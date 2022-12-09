package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.DAOInstances

class LayoutTemplate(private val dao: DAOInstances) : Template<HTML> {
    lateinit var content: String
    lateinit var tableId: String

    override fun HTML.apply() {
        head {
            link(rel = "icon", href = "/static/logo.png", type="image/png")
            link(rel = "stylesheet", href = "/static/style.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/homeStyle.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/newFeastStyle.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/contactStyle.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/searcherStyle.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/detailStyle.css", type = "text/css")
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
                            a {
                                href = "/fiestaspatronales/home"
                                +"INICIO | "
                            }
                        }
                        li{
                            a {
                                href = "/fiestaspatronales/searcher"
                                +"BUSCADOR | "
                            }
                        }
                        li{
                            a {
                                href = "/fiestaspatronales/contact"
                                +"CONTACTO | "
                            }
                        }
                        li{
                            a {
                                href = "/fiestaspatronales/newFeast"
                                +"APORTAR | "
                            }
                        }
                        li{
                            a {
                                href = "/fiestaspatronales/login"
                                +"UNLOGIN | "
                            }
                        }
                        li{
                            a {
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
                        "home" -> this.insert(HomeTemplate(dao), TemplatePlaceholder())
                        "searcher" -> this.insert(SearcherTemplate(dao), TemplatePlaceholder())
                        "contact" -> this.insert(ContactTemplate(), TemplatePlaceholder())
                        "api" -> this.insert(ApiTemplate(), TemplatePlaceholder())
                        "newFeast" -> this.insert(NewFeastTemplate(dao), TemplatePlaceholder())
                        "detail" -> this.insert(DetailTemplate(dao,tableId), TemplatePlaceholder())
                    }
                }
            }
        }
    }
}