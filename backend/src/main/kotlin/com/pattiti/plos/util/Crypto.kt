package com.pattiti.plos.util

import org.jasypt.util.text.AES256TextEncryptor

object Crypto {
    private val encryptor = AES256TextEncryptor().apply {
        setPassword(System.getenv("PLOS_ENC_KEY"))
    }

    fun encrypt(plain: String): String = encryptor.encrypt(plain)
    fun decrypt(cipher: String): String = encryptor.decrypt(cipher)
}
