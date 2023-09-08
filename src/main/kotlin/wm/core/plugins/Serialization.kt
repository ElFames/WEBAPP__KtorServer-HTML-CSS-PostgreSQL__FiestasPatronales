package wm.core.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import wm.data.database.DataManager
import wm.ui.routes.jsonsRouting
import wm.ui.routes.webRouting

fun Application.configureSerialization(dao: DataManager) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        webRouting(dao)
        static("/static") {
            resources("static")
        }
        authenticate("auth-basic") {
            jsonsRouting(dao)
        }
    }
}
