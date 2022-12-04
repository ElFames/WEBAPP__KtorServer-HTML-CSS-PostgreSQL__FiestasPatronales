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
            link(rel = "stylesheet", href = "/static/homeStyle.css", type = "text/css")
            link(rel = "stylesheet", href = "/static/contactStyle.css", type = "text/css")
        }
        body {
            header {
                a("/fiestaspatronales") {
                    img(classes="logo") {
                        src = "/static/logo.png"
                        alt = "logo"
                    }
                }
                img {
                    src = "/static/carnaval.JPG"
                    alt = "logo"
                }
                img {
                    src = "/static/fallas.JPG"
                    alt = "logo"
                }
                img {
                    src = "/static/feriabril.JPG"
                    alt = "logo"
                }
                img {
                    src = "/static/pilar.JPG"
                    alt = "logo"
                }
                img {
                    src = "/static/sanfermin.JPG"
                    alt = "logo"
                }
                img {
                    src = "/static/sanjuan.JPG"
                    alt = "logo"
                }
                img {
                    src = "/static/semanasanta.JPG"
                    alt = "logo"
                }
            }

            main {
                div("menu") {
                    nav {
                        ul {
                            li {
                                a(classes="enlace") {
                                    href = "/fiestaspatronales"
                                    +"Inicio-Resumen"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = "/fiestaspatronales/searcher"
                                    +"Buscador de fiestas"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = "/fiestaspatronales/popular"
                                    +"Fiestas populares"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = "/fiestaspatronales/nextRoute"
                                    +"Proximas Rutas"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = "/fiestaspatronales/contact"
                                    +"Contactanos"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = "/fiestaspatronales/newFeast"
                                    +"Sube una fiesta"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = "/fiestaspatronales/login"
                                    +"Cambiar de usuario"
                                }
                            }
                        }
                        ul {
                            li{
                                a(classes="enlace") {
                                    href = "/fiestaspatronales/api"
                                    +"Endpoints de la Api"
                                }
                            }
                        }
                    }
                }
                div ("maincontent"){
                    when (content) {
                        "home" -> this.insert(HomeTemplate(feastDAO), TemplatePlaceholder())
                        "searcher" -> this.insert(SearcherTemplate(feastDAO), TemplatePlaceholder())
                        "popular" -> this.insert(PopularTemplate(feastDAO), TemplatePlaceholder())
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