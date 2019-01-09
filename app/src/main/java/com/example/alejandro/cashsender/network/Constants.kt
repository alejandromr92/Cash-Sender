package com.example.alejandro.cashsender.network

interface Constants {
    companion object {
        const val READ_TIME_OUT = 60L
        const val WRITE_TIME_OUT = 60L
        const val CONNECT_TIME_OUT = 60L
        const val MILIS = 1000L

        const val PUBLIC_KEY = "0e469aac93ed81b266b2d4cd94288006"
        const val PRIVATE_KEY = "a7625563d4fcb27e849b584104e554e49fde8d87"
        const val CHARACTERS_LIMIT = 50

        const val TIMESTAMP = "ts"
        const val HASH = "hash"
        const val API_KEY = "apikey"
        const val LIMIT = "limit"
    }
}
