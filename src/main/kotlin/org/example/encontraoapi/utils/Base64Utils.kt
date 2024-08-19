package org.example.encontraoapi.utils

import java.util.Base64

object Base64Utils {

    fun encode(input: String): String {
        return Base64.getEncoder().encodeToString(input.toByteArray())
    }

    fun encode(bytes: ByteArray): String {
        return Base64.getEncoder().encodeToString(bytes)
    }

    fun decode(base64String: String): String {
        val decodedBytes = Base64.getDecoder().decode(base64String)
        return String(decodedBytes)
    }

    fun decodeToBytes(base64String: String): ByteArray {
        val base64Image = base64String.substringAfter("base64,")
        return Base64.getDecoder().decode(base64Image)
    }
}