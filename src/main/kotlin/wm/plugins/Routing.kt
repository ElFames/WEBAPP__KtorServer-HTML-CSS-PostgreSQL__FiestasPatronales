package wm.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import wm.data.DAOInstances
import wm.routes.jsonsRouting
import wm.routes.webRouting

fun Application.configureRouting(dao: DAOInstances) {
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