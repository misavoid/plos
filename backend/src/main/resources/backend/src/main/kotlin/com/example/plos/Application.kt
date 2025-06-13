package com.pattiti.plos

import com.typesafe.config.ConfigFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database

fun main() {
    // 1) Load application.conf from resources
    val config = HoconApplicationConfig(ConfigFactory.load())

    // 2) Read the `ktor.dataSource` block
    val ds       = config.config("ktor.dataSource")
    val url      = ds.property("url").getString()
    val driver   = ds.property("driver").getString()
    val user     = ds.property("user").getString()
    val password = ds.property("password").getString()

    // 3) Connect Exposed
    Database.connect(
        url = url,
        driver = driver,
        user = user,
        password = password
    )
    println("✅ Connected to Postgres via HOCON!")

    // 4) Start a bare‐bones Ktor server
    embeddedServer(Netty, port = 8080) {
        // here you can install routing, etc.
        // e.g. install(Routing) { get("/") { call.respondText("Hello!") } }
    }.start(wait = true)
}
