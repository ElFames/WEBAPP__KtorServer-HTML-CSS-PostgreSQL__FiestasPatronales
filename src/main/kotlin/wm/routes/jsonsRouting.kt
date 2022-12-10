package wm.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import wm.data.DAOInstances

fun Route.jsonsRouting(dao: DAOInstances) {
    authenticate("auth-basic") {
        route("/api") {
            get {
                call.respondText("Hello, ${call.principal<UserIdPrincipal>()?.name}!")
            }
            get("/") {
                call.respondText("Hello, ${call.principal<UserIdPrincipal>()?.name}!")
            }
            get("/feasts") {
                if (dao.feastDAO.getJsonsFeasts().isEmpty())
                    call.respondText("No feast found", status = HttpStatusCode.NotFound)
                else call.respond(dao.feastDAO.getJsonsFeasts())
            }
            get("/citys") {
                if (dao.cityDAO.getJsonsCitys().isEmpty())
                    call.respondText("No city found", status = HttpStatusCode.NotFound)
                else call.respond(dao.cityDAO.getJsonsCitys())
            }
            get("/towns") {
                if (dao.townDAO.getJsonsTowns().isEmpty())
                    call.respondText("No town found", status = HttpStatusCode.NotFound)
                else call.respond(dao.townDAO.getJsonsTowns())
            }
            get("/{id?}") {
                val id = call.parameters["id"] ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
                val feast = dao.feastDAO.getJsonFeast(id.toInt())
                if (feast.id==null)
                    call.respondText("Feast not exist with id $id", status = HttpStatusCode.NotFound)
                else call.respond(feast)
            }
            put("/{id}/description") {
            }
        }
    }
}