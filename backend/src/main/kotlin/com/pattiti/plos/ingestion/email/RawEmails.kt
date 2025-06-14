package com.pattiti.plos.ingestion.email

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object RawEmails : Table("raw_emails") {
    val id           = integer("id").autoIncrement()
    override val primaryKey = PrimaryKey(id, name = "pk_raw_emails_id")
    val accountId    = reference("account_id", ImapAccounts.id)
    val messageId    = varchar("message_id", length = 512)
    val receivedDate = datetime("received_date")
    val rawContent   = text("raw_content")
    val fetchedAt    = datetime("fetched_at")
}
