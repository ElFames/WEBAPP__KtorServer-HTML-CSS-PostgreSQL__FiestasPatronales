package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.DAOInstances

class LayoutTemplate(private val dao: DAOInstances) : Template<HTML> {
    lateinit var content: String
    lateinit var tableId: String
    var search: String? = null

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
                a("/") {
                    img(classes="logo") {
                        src = "/static/logo.png"
                        alt = "logo"
                    }
                }
                nav {
                    ul("menu") {
                        li {
                            a {
                                href = "/home"
                                +"INICIO | "
                            }
                        }
                        li{
                            a {
                                href = "/searcher"
                                +"BUSCADOR | "
                            }
                        }
                        li{
                            a {
                                href = "/contact"
                                +"CONTACTO | "
                            }
                        }
                        li{
                            a {
                                href = "/newFeast"
                                +"APORTAR | "
                            }
                        }
                        li{
                            a {
                                href = "/login"
                                +"UNLOGIN | "
                            }
                        }
                        li{
                            a {
                                href = "/api"
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
                        "searcher" -> this.insert(SearcherTemplate(dao, search), TemplatePlaceholder())
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