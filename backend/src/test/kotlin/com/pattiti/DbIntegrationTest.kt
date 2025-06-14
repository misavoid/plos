package com.yourorg.com.pattiti

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.containers.PostgreSQLContainer


@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DbIntegrationTest {

    companion object {
        @Container
        val postgres = PostgreSQLContainer<Nothing>("postgres:15-alpine").apply {
            withDatabaseName("plos_test")
            withUsername("test")
            withPassword("test")
        }
    }

    init {
        // Connect Exposed to our Testcontainer’s JDBC URL
        Database.Companion.connect(
            url = postgres.jdbcUrl,
            driver = "org.postgresql.Driver",
            user = postgres.username,
            password = postgres.password
        )

        // Define a simple table for test
        val testTable = object : Table("test_table") {
            val id   = integer("id").autoIncrement()
            val name = varchar("name", length = 50)
            override val primaryKey = PrimaryKey(id)
        }

        // Create the schema
        SchemaUtils.create(testTable)
    }

    @Test
    fun `can write and read from test_table`() {
        val testTable = object : Table("test_table") {
            val id   = integer("id").autoIncrement()
            val name = varchar("name", length = 50)
            override val primaryKey = PrimaryKey(id)
        }

        // Insert a row
        testTable.insert {
            it[name] = "hello"
        }

        // Query it back
        val results = testTable.selectAll().map { it[testTable.name] }
        Assertions.assertEquals(listOf("hello"), results)
    }
}