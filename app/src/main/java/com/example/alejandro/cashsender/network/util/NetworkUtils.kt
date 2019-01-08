package com.example.alejandro.cashsender.network.util

import java.security.MessageDigest

abstract class NetworkUtils {
    companion object {
        fun generateMd5(ts: String, privateKey: String, publicKey: String): String {
            val pass = (ts + privateKey + publicKey)

            val md = MessageDigest.getInstance("MD5")
            val digested = md.digest(pass.toByteArray())
            return digested.joinToString("") {
                String.format("%02x", it)
            }
        }
    }
}