package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO

class LoginTemplate: Template<HTML> {

    override fun HTML.apply() {
        head {

        }
        body {
            form {
                action = "/fiestaspatronales/checkLogin"
                encType = FormEncType.textPlain
                method = FormMethod.post

                input {
                    id = "nickname"
                    name = "nickname"
                    type = InputType.text
                    placeholder = "Nombre de usuario"
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