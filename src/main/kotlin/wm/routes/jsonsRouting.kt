package wm.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionScope
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
            get("all") {
                if (dao.feastDAO.getAllData().isEmpty())
                    call.respondText("No feast found", status = HttpStatusCode.NoContent)
                else call.respondText("${call.respond(dao.feastDAO.getAllData())}")
            }
            get("/feasts") {
                if (dao.feastDAO.getAllFeasts().empty())
                    call.respondText("No feast found", status = HttpStatusCode.NotFound)
                else call.respond(dao.feastDAO.getAllFeasts())
            }
            get("/citys") {
                if (dao.cityDAO.getAllCitys().empty())
                    call.respondText("No city found", status = HttpStatusCode.NotFound)
                else call.respond(dao.cityDAO.getAllCitys())
            }
            get("/towns") {
                if (dao.townDAO.getAllTowns().empty())
                    call.respondText("No town found", status = HttpStatusCode.NotFound)
                else call.respond(dao.townDAO.getAllTowns())
            }
            get("/{id?}") {
                val id = call.parameters["id"]
                    ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
                val feast = dao.feastDAO.getFeastById(id.toInt())
                    ?: return@get call.respondText("Feast not exist with id $id", status = HttpStatusCode.NotFound)
                call.respond(feast)
            }
            put("/{id}/description") {
            }
        }
    }
}