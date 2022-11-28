package wm.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        // route.elnombredelarchivo()
        static("/static") {
            resources("staticFiles")
        }
    }
}
