package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.data.FeastDAO

class LayoutTemplate(val feastDAO: FeastDAO) : Template<HTML> {
    lateinit var content: String

    override fun HTML.apply() {
        head {
            link(rel = "icon", href = "/static/logo.png", type="image/png")
            link(rel = "stylesheet", href = "/static/style.css", type = "text/css")
        }
        body {
            header {
                img {
                    src = "/static/logo.png"
                    alt = "logo"
                }
            }

            main {
                div("menu") {
                    nav {
                        ul {
                            li {
                                a(classes="enlace") {
                                    href = ""
                                    +"Inicio-Resumen"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = ""
                                    +"Buscador de fiestas"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = ""
                                    +"Fiestas populares"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = ""
                                    +"Proximas Rutas"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = ""
                                    +"Contactanos"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = ""
                                    +"Endpoint Api"
                                }
                            }
                        }
                    }
                }
                div ("maincontent"){
                    when (content) {
                        "home" -> this.insert(HomeTemplate(feastDAO), TemplatePlaceholder())
                        //"newFilm" -> this.insert(nuevoTemplate(), TemplatePlaceholder())
                        //"infoFilm" -> this.insert(nuevoTemplate(), TemplatePlaceholder())
                    }
                }
            }
        }
    }
}