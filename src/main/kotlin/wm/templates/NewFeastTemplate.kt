package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*

class NewFeastTemplate : Template<FlowContent> {
    override fun FlowContent.apply() {
        div {
            postForm {
                action = "/fiestaspatronales/addFeast"
                encType = FormEncType.multipartFormData

                input {
                    name = "nombre"
                    type = InputType.text
                    placeholder = "Nombre de la fiesta"
                    required = true
                }
                br {}

                input {
                    name = "fechas"
                    type = InputType.text
                    placeholder = "dd/mm - dd/mm"
                    required = true
                }
                br {}

                input {
                    name = "ciudad"
                    type = InputType.text
                    placeholder = "Ciudad"
                    required = true
                }
                br {}

                input {
                    name = "pueblo"
                    type = InputType.text
                    placeholder = "Pueblo"
                    required = true
                }
                br {}

                input {
                    name = "descripcion"
                    type = InputType.text
                    placeholder = "Descripcion"
                }
                br {}

                label {
                    htmlFor = "image"
                    +"Subir Imagen"
                }
                br {}
                input {
                    name = "image"
                    type = InputType.file
                    required = true
                }
                br {}

                input(classes = "sendButton"){
                    type = InputType.submit
                    value = "Subir"
                    attributes["aria-selected"] = "true"
                }
            }

        }
    }
}