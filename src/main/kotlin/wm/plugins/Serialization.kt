package wm.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import wm.routes.fiestasPatronalesRouting

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        fiestasPatronalesRouting()
    }
}
