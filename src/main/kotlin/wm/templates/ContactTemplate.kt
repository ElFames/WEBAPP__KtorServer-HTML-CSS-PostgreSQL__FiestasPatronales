package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*

class ContactTemplate: Template<FlowContent> {

    override fun FlowContent.apply() {
        div("mainbox") {
            table {
                tr {
                    th {
                        +"Envianos tu comentario o sugerencia!"
                    }
                }
                tr {
                    td {
                        +"Autores responsables de esta obra de arte:"
                        br
                        +" - Unos tios del Itb"
                        br
                        br
                        +"Para un kebab en condiciones llamar a: 659123648"
                        br
                        +"Para un coche que se rompe solo llamar a: 621489224"
                        br
                        br
                        +"Si de verdad vas a dejar un comentario puedes hacerlo aquí, aunque yo me lo pensaría antes de pulsar ese botón"
                        br
                        br
                        form {
                            action = "/fiestaspatronales/comentario"
                            encType = FormEncType.textPlain
                            method = FormMethod.post

                            input(classes="comentario") {
                                name = "comentario"
                                type = InputType.text
                                placeholder = "Escribe tu comentario"
                                required = true
                            }
                            input(classes="button"){
                                type = InputType.submit
                                value = "Enviar"
                                attributes["aria-selected"] = "true"
                            }
                        }
                    }
                }
            }
        }
    }
}