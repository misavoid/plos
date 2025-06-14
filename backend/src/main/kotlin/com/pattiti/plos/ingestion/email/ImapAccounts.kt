package com.pattiti.plos.ingestion.email

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object ImapAccounts : Table("imap_accounts") {
    val id                = integer("id").autoIncrement()
    override val primaryKey = PrimaryKey(id, name = "pk_imap_accounts_id")
    val host              = varchar("host", length = 255)
    val port              = integer("port").default(143)
    val username          = varchar("username", length = 255)
    val passwordEncrypted = text("password_encrypted")
    val useSsl            = bool("use_ssl").default(true)
    val createdAt         = datetime("created_at")
}
