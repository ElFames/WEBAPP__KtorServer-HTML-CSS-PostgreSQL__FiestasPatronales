package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.DAOInstances

data class NextRouteTemplate(val dao: DAOInstances) : Template<FlowContent> {

    override fun FlowContent.apply() {
        div("mainbox") {
            iframe {
                src = dao.urlMaps+dao.defaultLocation
                width = "700"
                height = "450"
                style = "border:0;"
            }
        }
    }
}