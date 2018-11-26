package com.cyrillrx.utils

/**
 * @author Cyril Leroux
 *          Created on 16/05/2018
 */

fun Any?.className(): String =
        if (this == null) {
            "class==null"
        } else {
            this::class.java.simpleName
        }