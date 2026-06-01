package com.pdmcourse2026.basictemplate.data.api

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
  val client = HttpClient(OkHttp) {
    // Parseo automático de JSON
    install(ContentNegotiation) {
      json(Json {
        ignoreUnknownKeys = true
      })
    }

    // Plugin de logging
    install(Logging) {
      logger = object : Logger {
        override fun log(message: String) {
          Log.d("KtorClient", message)
        }
      }
      level = LogLevel.ALL
    }

    // Configuración aplicada a todas las peticiones
    defaultRequest {
      header(HttpHeaders.Accept, "application/json")
    }
  }

  val httpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
      json(Json {
        ignoreUnknownKeys = true
      })
    }
    install(DefaultRequest) {
      header("6be5cae0-fc53-4671-b015-1e2a90fef7e4", "6be5cae0-fc53-4671-b015-1e2a90fef7e4")
    }
  }

}