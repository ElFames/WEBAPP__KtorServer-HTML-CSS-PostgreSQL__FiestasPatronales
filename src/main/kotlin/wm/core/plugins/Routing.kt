package wm.core.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import wm.data.database.DataManager
import wm.ui.routes.jsonsRouting
import wm.ui.routes.webRouting

fun Application.configureRouting(dao: DataManager) {
    install(Authentication) {
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                if (credentials in dao.userDAO.usersForApi)
                    UserIdPrincipal(credentials.name)
                else null
            }
        }
    }
    routing {
        webRouting(dao)
        jsonsRouting(dao)

        static("/static") {
            resources("static")
        }
    }
}