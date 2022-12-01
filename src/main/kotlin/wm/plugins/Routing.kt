package wm.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import wm.routes.fiestasPatronalesRouting

fun Application.configureRouting() {
    routing {
        fiestasPatronalesRouting()
        static("/static") {
            resources("static")
        }
    }
}