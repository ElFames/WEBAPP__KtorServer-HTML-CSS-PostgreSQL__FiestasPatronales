package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO

class LoginTemplate: Template<HTML> {

    override fun HTML.apply() {
        head {
            link(rel = "icon", href = "/static/logo.png", type="image/png")
            link(rel = "stylesheet", href = "/static/loginStyle.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/style.css", type = "text/css")
        }
        body {
            div("login") {
                div("mainbox") {
                    img {
                        src = "/static/logo.png"
                        alt = "logo"
                    }
                    form {
                        action = "/fiestaspatronales/checkLogin"
                        encType = FormEncType.textPlain
                        method = FormMethod.post

                        input {
                            id = "nickname"
                            name = "nickname"
                            type = InputType.text
                            placeholder = "Usuario"
                            required = true
                        }
                        br {}
                        input {
                            id = "password"
                            name = "password"
                            type = InputType.password
                            placeholder = "Contraseña"
                            required = true
                        }
                        br {}

                        input(classes = "button"){
                            type = InputType.submit
                            value = "Login"
                            attributes["aria-selected"] = "true"
                        }
                        input(classes = "button"){
                            formAction = "/fiestaspatronales/newUser"
                            type = InputType.submit
                            value = "Registrar"
                            attributes["aria-selected"] = "true"
                        }
                    }
                }
            }
        }
    }
}