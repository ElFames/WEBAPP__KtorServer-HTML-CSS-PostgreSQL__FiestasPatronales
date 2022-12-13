package wm.templates

import kotlinx.html.*

fun FlowContent.endpointsTemplate() {

    div("mainbox") {
        h2 {
            +"Consigue datos en json"
        }
        p { +"Esto es una mini introduccion para que te pongas a leer un rato, no hay secreto aqui el codigo es libre siempre y cuando pagues pero es gratis si no es dinero lo que pagas."}

        a("/api/feasts") {
            p { +"GET /api/feasts"}
        }
        a("/api/citys") {
            p { +"GET /api/citys"}
        }
        a("/api/towns") {
            p {+"GET /api/towns"}
        }
        a("/api/1") {
            p {+"GET /api/1" }
        }
        p {
            +"PUT /api/{id}/description"
        }
        p { +"Las credenciales de la api seran las mismas que las del usuario de la web" }
    }
}