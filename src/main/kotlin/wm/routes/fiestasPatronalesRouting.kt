package wm.routes

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import wm.data.FeastDAO
import wm.data.UsersDAO
import wm.functions.multipartDataToFeast
import wm.models.User
import wm.templates.*

fun Route.fiestasPatronalesRouting() {
    val feastDAO = FeastDAO()
    val userDAO = UsersDAO()
    var currentUser: User? = null

    route("/fiestaspatronales") {

        get {
            call.respondRedirect("fiestaspatronales/home")
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
                        h1 { +"ERROR 300 ---> No existe el usuario: $nickname..." }
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
            val feastDataInList = multipartDataToFeast(call.receiveMultipart())
            feastDAO.createCityAndTownIfNotExists(feastDataInList["town"]!!,feastDataInList["city"]!!)
            feastDAO.addFeast(feastDataInList)
            call.respondRedirect("home")
        }

        get("home") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "home"
            }
        }
        get("searcher") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "searcher"
            }
        }
        get("newFeast") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "newFeast"
            }
        }
        get("popular") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "popular"
            }
        }
        get("nextRoute") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "nextRoute"
            }
        }
        get("contact") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
                this.content = "contact"
            }
        }
        get("api") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate(feastDAO)) {
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
