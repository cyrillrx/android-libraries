package com.cyrillrx.android.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.cyrillrx.utils.deserialize
import com.cyrillrx.utils.serialize

fun Context.getPreferences(): SharedPreferences? =
        PreferenceManager.getDefaultSharedPreferences(applicationContext)

@Deprecated("Use Context.editPref()", replaceWith = ReplaceWith("editPref()"))
fun Context.edit() = getPreferences()?.edit()

fun Context.editPref() = getPreferences()?.edit()

fun Context.clearPrefKey(key: String) = editPref()?.remove(key)?.apply()

/**
 * Retrieves a string value from the shared preferences.
 * Clears the stored field if an error occurs.
 *
 * @param key     The name of the preference to retrieve.
 * @return The preference value if it exists, or defaultValue.
 */
fun Context.getPrefString(key: String, defaultValue: String? = null): String? {

    try {
        return getPreferences()?.getString(key, defaultValue) ?: defaultValue

    } catch (e: Exception) {
        // If a problem occurred while parsing, better clear the stored field.
        clearPrefKey(key)
    }

    return defaultValue
}

/**
 * Retrieves an integer value from the shared preferences.
 * Clears the stored field if an error occurs.
 *
 * @param key     The name of the preference to retrieve.
 * @return The preference value if it exists, or defaultValue.
 */
fun Context.getPrefInt(key: String, defaultValue: Int = Int.MIN_VALUE): Int {

    try {
        return getPreferences()?.getInt(key, defaultValue) ?: defaultValue
    } catch (e: Exception) {
        // If a problem occurred while parsing, better clear the stored field.
        clearPrefKey(key)
    }

    return defaultValue
}

/**
 * Retrieves a long value from the shared preferences.
 * Clears the stored field if an error occurs.
 *
 * @param key     The name of the preference to retrieve.
 * @return The preference value if it exists, or defaultValue.
 */
fun Context.getPrefLong(key: String, defaultValue: Long = Long.MIN_VALUE): Long {

    try {
        return getPreferences()?.getLong(key, defaultValue) ?: defaultValue
    } catch (e: Exception) {
        // If a problem occurred while parsing, better clear the stored field.
        clearPrefKey(key)
    }

    return defaultValue
}

/**
 * Retrieves a float value from the shared preferences.
 * Clears the stored field if an error occurs.
 *
 * @param key     The name of the preference to retrieve.
 * @return The preference value if it exists, or defaultValue.
 */
fun Context.getPrefFloat(key: String, defaultValue: Float = Float.MIN_VALUE): Float {

    try {
        return getPreferences()?.getFloat(key, defaultValue) ?: defaultValue
    } catch (e: Exception) {
        // If a problem occurred while parsing, better clear the stored field.
        clearPrefKey(key)
    }

    return defaultValue
}

/**
 * Retrieves a boolean value from the shared preferences.
 * Clears the stored field if an error occurs.
 *
 * @param key     The name of the preference to retrieve.
 * @return The preference value if it exists, or defaultValue.
 */
fun Context.getPrefBoolean(key: String, defaultValue: Boolean = false): Boolean {

    try {
        return getPreferences()?.getBoolean(key, defaultValue) ?: defaultValue
    } catch (e: Exception) {
        // If a problem occurred while parsing, better clear the stored field.
        clearPrefKey(key)
    }

    return defaultValue
}

/**
 * Serializes and saves the given object to the shared preferences.
 * @return True if the object has been saved.
 * */
fun Context.saveObject(key: String, any: Any?): Boolean {
    if (any == null) {
        return false
    }
    editPref()?.putString(key, any.serialize())?.apply() ?: return false
    return true
}

/**
 * Gets the serialized object from the shared preferences and de-serializes it.
 *
 * @param key     The name of the preference to retrieve.
 * @param clazz   The type of the object.
 * @return The de-serialized object or null.
 */
fun <T> Context.loadObject(key: String, clazz: Class<T>): T? {

    val serializedData = getPrefString(key)
    if (serializedData == null || serializedData.isBlank()) {
        return null
    }

    return try {
        return serializedData.deserialize(clazz)
    } catch (e: Exception) {
        // If a problem occurred while parsing, better clear the stored field.
        clearPrefKey(key)
        null
    }
}

/**
 * Gets the serialized object from the shared preferences and de-serializes it.
 *
 * @param key     The name of the preference to retrieve.
 * @return The de-serialized object or null.
 */
inline fun <reified T> Context.loadObject(key: String): T? = loadObject(key, T::class.java)
