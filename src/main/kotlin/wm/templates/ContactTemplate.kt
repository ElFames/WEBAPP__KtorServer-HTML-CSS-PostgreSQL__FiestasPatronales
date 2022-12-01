package wm.templates

import io.ktor.server.html.*
import kotlinx.html.FlowContent
import kotlinx.html.h1
import wm.data.FeastDAO

data class ContactTemplate(val feastDAO: FeastDAO) : Template<FlowContent> {

    override fun FlowContent.apply() {
        h1 {
            +"Contactanos"
        }
    }
}