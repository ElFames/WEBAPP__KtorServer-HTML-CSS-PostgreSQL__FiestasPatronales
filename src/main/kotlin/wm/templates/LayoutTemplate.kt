package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO

class LayoutTemplate(private val feastDAO: FeastDAO) : Template<HTML> {
    lateinit var content: String
    override fun HTML.apply() {
        head {
            link(rel = "icon", href = "/static/logo.png", type="image/png")
            link(rel = "stylesheet", href = "/static/style.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/newFeastStyle.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/contactStyle.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/searcherStyle.css", type = "text/css")
        }
        body {
            header {
                a("/fiestaspatronales") {
                    img(classes="logo") {
                        src = "/static/logo.png"
                        alt = "logo"
                    }
                }
                nav {
                    ul("menu") {
                        li {
                            a(classes="enlace") {
                                href = "/fiestaspatronales/home"
                                +"INICIO | "
                            }
                        }
                        li{
                            a(classes="enlace") {
                                href = "/fiestaspatronales/searcher"
                                +"BUSCADOR | "
                            }
                        }
                        li{
                            a(classes="enlace") {
                                href = "/fiestaspatronales/nextRoute"
                                +"RUTAS | "
                            }
                        }
                        li{
                            a(classes="enlace") {
                                href = "/fiestaspatronales/contact"
                                +"CONTACTO | "
                            }
                        }
                        li{
                            a(classes="enlace") {
                                href = "/fiestaspatronales/newFeast"
                                +"APORTAR | "
                            }
                        }
                        li{
                            a(classes="enlace") {
                                href = "/fiestaspatronales/login"
                                +"UNLOGIN | "
                            }
                        }
                        li{
                            a(classes="enlace") {
                                href = "/fiestaspatronales/api"
                                +"API"
                            }
                        }
                    }
                }
            }
            main {
                div ("maincontent"){
                    when (content) {
                        "home" -> this.insert(HomeTemplate(feastDAO), TemplatePlaceholder())
                        "searcher" -> this.insert(SearcherTemplate(feastDAO), TemplatePlaceholder())
                        "nextRoute" -> this.insert(NextRouteTemplate(feastDAO), TemplatePlaceholder())
                        "contact" -> this.insert(ContactTemplate(), TemplatePlaceholder())
                        "api" -> this.insert(ApiTemplate(), TemplatePlaceholder())
                        "newFeast" -> this.insert(NewFeastTemplate(), TemplatePlaceholder())
                    }
                }
            }
        }
    }
}