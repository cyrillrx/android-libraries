package com.cyrillrx.utils

import android.util.Base64
import java.math.BigInteger
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * @author Cyril Leroux
 *          Created on 20/09/2018
 */

/** Keyed-Hash Message Authentication Code (HMAC) key using SHA-512 as the hash.  */
private const val ALGORITHM_HMAC_SHA512 = "HmacSHA512"

/** Maximum length of the new `BigInteger` in bits.  */
private const val NUM_BITS = 130

/** Radix base to be used for the string representation.  */
private const val RADIX = 32

fun String.base64Encode(): String? = try {
    val byteArray = toByteArray(Charsets.UTF_8)
    Base64.encodeToString(byteArray, Base64.NO_WRAP)

} catch (e: Exception) {
    null
}

fun String.signature(secret: String): String? = try {

    val key = SecretKeySpec(secret.toByteArray(Charsets.UTF_8), ALGORITHM_HMAC_SHA512)

    val hMacSha512 = Mac.getInstance(ALGORITHM_HMAC_SHA512)
    hMacSha512.init(key)

    val byteArray = toByteArray(Charsets.UTF_8)

    val macData = hMacSha512.doFinal(byteArray)
    Base64.encodeToString(macData, Base64.NO_WRAP)

} catch (e: Exception) {
    null
}

fun buildNonce(): String = BigInteger(NUM_BITS, SecureRandom()).toString(RADIX)