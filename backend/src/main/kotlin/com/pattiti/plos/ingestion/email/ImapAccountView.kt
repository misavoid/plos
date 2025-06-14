package com.pattiti.plos.ingestion.email

import kotlinx.serialization.Serializable

@Serializable
data class ImapAccountView(
    val id: Int,
    val host: String,
    val port: Int,
    val username: String,
    val useSsl: Boolean
)
