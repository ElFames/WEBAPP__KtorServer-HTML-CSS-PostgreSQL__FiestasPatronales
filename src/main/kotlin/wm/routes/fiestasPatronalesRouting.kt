package wm.routes

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import wm.data.DAOInstances
import wm.functions.multipartDataToFeast
import wm.models.User
import wm.templates.*

fun Route.fiestasPatronalesRouting() {
    val dao = DAOInstances()
    val feastDAO = dao.feastDAO
    val userDAO = dao.userDAO
    var currentUser: User? = userDAO.getUser("admin","admin")

    route("/fiestaspatronales") {

        get {
            call.respondRedirect("fiestaspatronales/home")
        }
        get("/") {
            call.respondRedirect("home")
        }
        get("login") {
            call.respondHtmlTemplate(LoginTemplate()) {}
        }

        post("checkLogin") {
            val textForm = call.receiveText()
            val params = getParamsList(textForm)
            val nickname = params[0]
            val password = params[1]

            if (userDAO.checkUser(nickname, password)) {
                currentUser = userDAO.getUser(nickname, password)
                call.respondRedirect("home")
            } else {
                call.respondHtml {
                    body {
                        h1 { +"Nombre y/o contraseña incorrecta para el usuario: $nickname..." }
                    }
                }
            }
        }
        post("newUser") {
            val textForm = call.receiveText()
            val params = getParamsList(textForm)
            val nickname = params[0]
            val password = params[1]

            if (userDAO.checkUser(nickname, password)) {
                call.respondHtml {
                    body {
                        h1 { + "El usuario: $nickname ya esta registrado..." }
                    }
                }
            } else {
                userDAO.newUser(nickname, password)
                currentUser = userDAO.getUser(nickname, password)
                call.respondRedirect("home")
            }
        }
        post("addFeast") {
            val feastData = multipartDataToFeast(call.receiveMultipart())
            feastDAO.createFKsIfNotExist(feastData["town"]!!,feastData["city"]!!)
            feastDAO.addFeast(feastData)
            call.respondRedirect("home")
        }

        // Insert Templates:
        get("{id}") {
            if (currentUser==null) call.respondRedirect("login")
            val id = call.parameters["id"]
            call.respondHtmlTemplate(LayoutTemplate(dao)) {
                this.content = "detail"
                this.tableId = id ?: ""
            }
        }
        get("home") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(dao)) {
                this.content = "home"
            }
        }
        get("searcher") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(dao)) {
                this.content = "searcher"
            }
        }
        get("newFeast") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(dao)) {
                this.content = "newFeast"
            }
        }
        get("nextRoute") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(dao)) {
                this.content = "nextRoute"
            }
        }
        get("contact") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(dao)) {
                this.content = "contact"
            }
        }
        get("api") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(dao)) {
                this.content = "api"
            }
        }
    }
}
fun getParamsList(textForm: String): MutableList<String> {
    val params = mutableListOf<String>()
    textForm.reader().forEachLine { params.add(it.split('=')[1]) }
    return params
}