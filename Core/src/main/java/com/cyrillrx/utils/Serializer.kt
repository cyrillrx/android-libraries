package com.cyrillrx.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Serialization helper that uses [Gson] implementations to serialize and deserialize objects.
 *
 * @author Cyril Leroux
 *         Created on 31/03/2017
 */

private val DEFAULT: Gson = Gson()
private val PRETTY_PRINT: Gson = GsonBuilder()
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .serializeNulls()
        .create()
private val NO_HTML_ESCAPING: Gson = GsonBuilder()
        .disableHtmlEscaping()
        .create()

/** Serializes the receiver object with the default [Gson] implementation.  */
fun Any.serialize() = DEFAULT.toJson(this)

fun <T> String.deserialize(clazz: Class<T>): T = DEFAULT.fromJson(this, clazz)

/** Serializes the receiver object without escaping characters such as < > etc.  */
fun Any.serializeNoEscaping() = NO_HTML_ESCAPING.toJson(this)

fun <T> String.deserializeNoEscaping(clazz: Class<T>): T = NO_HTML_ESCAPING.fromJson(this, clazz)

/** Serializes and pretty prints the given object.  */
fun Any.prettyPrint() = PRETTY_PRINT.toJson(this)