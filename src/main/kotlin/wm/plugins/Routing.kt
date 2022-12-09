package wm.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import wm.routes.fiestasPatronalesRouting

fun Application.configureRouting() {
    val users = mutableListOf(
        UserPasswordCredential("admin","admin")
    )
    install(Authentication) {
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                if (credentials in users)
                    UserIdPrincipal(credentials.name)
                else null
            }
        }
    }
    routing {
        authenticate("auth-basic") {
            fiestasPatronalesRouting()
            static("/static") {
                resources("static")
            }
        }
    }
}