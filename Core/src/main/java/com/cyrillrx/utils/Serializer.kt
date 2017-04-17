package com.cyrillrx.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Serialization helper that uses [Gson] implementations to serialize and deserialize objects.

 * @author Cyril Leroux
 * *         Created 31/03/2017.
 */
object Serializer {

    private val DEFAULT: Gson = Gson()
    private val PRETTY_PRINT: Gson = GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .serializeNulls()
            .create()
    private val NO_HTML_ESCAPING: Gson = GsonBuilder()
            .disableHtmlEscaping()
            .create()

    /** Serializes the given object with the default [Gson] implementation.  */
    fun serialize(any: Any): String {
        return DEFAULT.toJson(any)
    }

    fun <T> deserialize(json: String, clazz: Class<T>): T {
        return DEFAULT.fromJson(json, clazz)
    }

    /** Serializes the given object without escaping characters such as < > etc.  */
    fun serializeNoEscaping(`object`: Any): String {
        return NO_HTML_ESCAPING.toJson(`object`)
    }

    fun <T> deserializeNoEscaping(json: String, clazz: Class<T>): T {
        return NO_HTML_ESCAPING.fromJson(json, clazz)
    }

    /** Serializes and pretty prints the given object.  */
    fun prettyPrint(any: Any): String {
        return PRETTY_PRINT.toJson(any)
    }

}