package com.cyrillrx.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Serialization helper that uses [Gson] implementations to serialize and deserialize objects.
 *
 * @author Cyril Leroux
 *         Created on 31/03/2017
 */

val defaultSerializer: Gson = Gson()

val prettyPrintSerializer: Gson = GsonBuilder()
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .serializeNulls()
        .create()

val noHtmlEscapeSerializer: Gson = GsonBuilder()
        .disableHtmlEscaping()
        .create()

/** Serializes the receiver object with the default [Gson] implementation.  */
fun Any.serialize(): String = defaultSerializer.toJson(this)

fun <T> String.deserialize(clazz: Class<T>): T = defaultSerializer.fromJson(this, clazz)

inline fun <reified T> String.deserialize(): T = deserialize(T::class.java)

/** Serializes the receiver object without escaping characters such as < > etc.  */
fun Any.serializeNoEscaping(): String = noHtmlEscapeSerializer.toJson(this)

inline fun <reified T> String.deserializeNoEscaping(): T =
        noHtmlEscapeSerializer.fromJson(this, T::class.java)

/** Serializes and pretty prints the given object.  */
fun Any.prettyPrint(): String = prettyPrintSerializer.toJson(this)