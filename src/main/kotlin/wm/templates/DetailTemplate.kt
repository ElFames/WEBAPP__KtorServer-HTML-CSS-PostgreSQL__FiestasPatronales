package wm.templates

import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction
import wm.models.feast.Feast

fun FlowContent.detailTemplate(feast: Feast?) {
    val urlMaps = "https://www.google.com/maps/embed?pb="
    transaction {
        div("mainbox") {
            if (feast != null) {
                div("detailbox") {
                    h2 {
                        +feast.name
                    }
                    img(classes="feastImg") {
                        src = "./images/${feast.image}"
                        alt = "foto-fiesta"
                    }
                    div("detailinfo") {
                        p {
                            +"Fecha"; br
                            b { +feast.dates }
                        }
                        p {
                            +"Ciudad"; br
                            b { +feast.city.name }}
                        p {
                            +"Pueblo"; br
                            b { +feast.town.name }}
                    }
                    p {
                        val desc = feast.description ?: "Sin descripcion"
                        +desc
                    }
                    h3 {
                        +"Aqui tienes la ubicación de la zona más proxima"
                    }
                    val location = feast.town.location?:feast.city.location?:""
                    iframe {
                        src = "$urlMaps$location"
                        width = "700"
                        height = "450"
                        style = "border:0;"
                    }
                }
            } else {
                div {
                    h1 {
                        +"Not found"
                    }
                }
            }
        }
    }
}