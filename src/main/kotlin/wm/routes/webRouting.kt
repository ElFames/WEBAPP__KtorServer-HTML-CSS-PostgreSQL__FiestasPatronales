package wm.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import wm.data.DAOInstances
import wm.functions.multipartDataToFeast
import wm.models.user.User
import wm.templates.*
import java.io.File

fun Route.webRouting(dao: DAOInstances) {
    val feastDAO = dao.feastDAO
    val userDAO = dao.userDAO
    val cityDAO = dao.cityDAO
    val townDAO = dao.townDAO
    val commentstorage = dao.commentstorage
    var currentUser: User? = null

    route("") {
        get {
            call.respondRedirect("home")
        }
        get("/") {
            call.respondRedirect("home")
        }
        get("login") {
            call.respondHtmlTemplate(LoginTemplate()) {}
        }
        get("/images/{imageName}") {
            val imageName = call.parameters["imageName"]
            val image = File("./images/$imageName")
            if(image.exists())
                call.respondFile(File("./images/$imageName"))
            else call.respondText("Image not found", status = HttpStatusCode.NotFound)
        }
        post("checkLogin") {
            val params = getParams(call.receiveText())
            if (userDAO.checkUser(params["nickname"],params["password"])) {
                currentUser = userDAO.getUser(params["nickname"],params["password"])
                call.respondRedirect("home")
            } else {
                call.respondHtml {
                    body { h1 { +"Nombre y/o contraseña incorrecta para el usuario: ${params["nickname"]}..." } }
                }
            }
        }
        post("newUser") {
            val params = getParams(call.receiveText())
            if (userDAO.checkUser(params["nickname"]!!,params["password"]!!)) {
                call.respondHtml {
                    body { h1 { + "El usuario: ${params["nickname"]}!! ya esta registrado..." } }
                }
            } else {
                userDAO.newUser(params["nickname"]!!,params["password"]!!)
                userDAO.usersForApi.add(UserPasswordCredential(params["nickname"]!!,params["password"]!!))
                currentUser = userDAO.getUser(params["nickname"]!!,params["password"]!!)
                call.respondRedirect("home")
            }
        }
        post("addFeast") {
            val feastData = multipartDataToFeast(call.receiveMultipart())
            feastDAO.addFeast(feastData)
            call.respondRedirect("home")
        }
        post("comentario") {
            val params = getParams(call.receiveText())
            val email = params["email"]?:"Anonimo"
            val text = params["comment"]!!
            commentstorage.saveComment(currentUser!!.nickname,email, text)
            call.respondRedirect("home")
        }

        /**
         * Insert Templates:
         */
        get("{id}") {
            if (currentUser==null) call.respondRedirect("login")
            val id = call.parameters["id"]
            val feast = feastDAO.getFeastById(id?.toInt()?:1)
            call.respondHtmlTemplate(LayoutTemplate()) {
                content {
                    detailTemplate(feast)
                }
            }
        }
        get("home") {
            if (currentUser==null) call.respondRedirect("login")
            val feasts = feastDAO.getAllFeastsGrupedByCity()
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content {
                    homeTemplate(feasts)
                }
            }
        }
        get("searcher") {
            if (currentUser==null) call.respondRedirect("login")
            val textToSearch = call.parameters
            val feasts = feastDAO.searchFeast(textToSearch["search"]?:"")
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content {
                    searcherTemplate(feasts)
                }
            }
        }
        get("newFeast") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content {
                    newFeastTemplate(townDAO.getAllTowns(),cityDAO.getAllCitys())
                }
            }
        }
        get("contact") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content {
                    contactTemplate()
                }
            }
        }
        get("endpoints") {
            if (currentUser==null) call.respondRedirect("login")
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content {
                    endpointsTemplate()
                }
            }
        }
    }
}
fun getParams(textForm: String): MutableMap<String, String> {
    val params = mutableMapOf<String,String>()
    var name: String
    var value: String
    textForm.reader().forEachLine {
        name = it.split('=')[0]
        value = it.split('=')[1]
        params[name] = value
    }
    return params
}
/**
 * Para proteger tu cuenta, a partir del 30 de mayo del 2022, Google dejará de admitir aplicaciones
 * y dispositivos de terceros que te pidan que inicies sesión en tu cuenta de Google usando solo tu
 * nombre de usuario y contraseña.
 */
/**
 * SimpleEmail().apply {
        hostName = "smtp.googlemail.com"
        setSmtpPort(465)
        setAuthenticator(DefaultAuthenticator("migueelamaya@gmail.com", ""))
        isSSLOnConnect = true
        setFrom("migueelamaya@gmail.com")
        subject = "Comentario web FiestasPatronales"
        setMsg(params["comment"])
        addTo(params["email"])
        send()
    }
 */