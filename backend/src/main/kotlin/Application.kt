package com.pattiti.plos.ingestion.email
import com.pattiti.plos.ingestion.email.AddImapAccountDto
import com.pattiti.plos.ingestion.email.ImapAccountView
import com.pattiti.plos.util.Crypto.encrypt


import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.routing

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        route("/api/imap") {
            // CREATE
            post {
                val dto = call.receive<AddImapAccountDto>()
                // TODO: encrypt dto.password
                transaction {
                    ImapAccounts.insert {
                        it[host]              = dto.host
                        it[port]              = dto.port
                        it[username]          = dto.username
                        it[passwordEncrypted] = encrypt(dto.password)
                        it[useSsl]            = dto.useSsl
                        it[createdAt]         = LocalDateTime.now()
                    }
                }
                call.respond(HttpStatusCode.Created)
            }

            // LIST
            get {
                val list = transaction {
                    ImapAccounts.selectAll().map {
                        ImapAccountView(
                            id       = it[ImapAccounts.id],
                            host     = it[ImapAccounts.host],
                            port     = it[ImapAccounts.port],
                            username = it[ImapAccounts.username],
                            useSsl   = it[ImapAccounts.useSsl]
                        )
                    }
                }
                call.respond(list)
            }

        }
    }
}
