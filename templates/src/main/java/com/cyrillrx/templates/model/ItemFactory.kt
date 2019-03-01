package com.cyrillrx.templates.model

/**
 * @author Cyril Leroux
 *         Created on 28/02/2019.
 */
interface ItemFactory {

    fun createHeader(input: Any?): Header

    fun createItem(input: Any?): Item
}