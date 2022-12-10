package wm.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import wm.routes.webRouting

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
