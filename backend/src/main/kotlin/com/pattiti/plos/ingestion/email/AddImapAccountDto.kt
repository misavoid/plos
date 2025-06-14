package com.pattiti.plos.ingestion.email
import com.pattiti.plos.ingestion.email.AddImapAccountDto
import com.pattiti.plos.ingestion.email.ImapAccountView


import kotlinx.serialization.Serializable

@Serializable
data class AddImapAccountDto(
    val host: String,
    val port: Int = 993,
    val username: String,
    val password: String,
    val useSsl: Boolean = true
)
