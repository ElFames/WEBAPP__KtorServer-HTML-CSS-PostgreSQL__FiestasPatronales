package wm.templates

import kotlinx.html.*

fun FlowContent.contactTemplate() {

    div("mainbox") {
        h2 {
            +"Envianos tu comentario o sugerencia!"
        }
        p {
            +"Autores responsables de esta obra de arte:"
            br
            +" - Unos tios del Itb"
        }
        p {
            +"Para un kebab en condiciones llamar a: 659123648"
            br
            +"Para un coche que se rompe solo llamar a: 621489224"
        }
        p {
            +"Dejanos tu correo y tu comentario, duda o sugerencia"
        }
        postForm {
            action = "/comentario"
            encType = FormEncType.textPlain

            input {
                id = "email"
                name = "email"
                type = InputType.email
                placeholder = "Escribe tu email si quieres una respuesta"
            }
            br
            input(classes="comment") {
                name = "comment"
                type = InputType.text
                placeholder = "Escribe tu comentario"
                required = true
            }
            br
            input(classes="button"){
                type = InputType.submit
                value = "Enviar"
                attributes["aria-selected"] = "true"
            }
        }
    }
}