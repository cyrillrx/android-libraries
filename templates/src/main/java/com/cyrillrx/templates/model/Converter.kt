package com.cyrillrx.templates.model

/**
 * @author Cyril Leroux
 *         Created on 28/02/2019.
 */
interface Converter {

    fun toHeader(input: Any?): Header

    fun toItem(input: Any?): Item
}