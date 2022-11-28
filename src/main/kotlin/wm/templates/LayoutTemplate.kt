package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*

class LayoutTemplate: Template<HTML> {
    lateinit var content: String

    override fun HTML.apply() {
        head {
            link(rel = "icon", href = "/static/logo.png", type="image/png")
            link(rel = "stylesheet", href = "/static/style.css", type = "text/css")
        }
        body {
            header {
                div {
                    img {
                        src = ""
                        alt = ""
                    }
                }
                nav {
                    ul() {
                        li {
                            a() {
                                href = ""
                                +""
                            }
                        }
                        li {
                            a(){
                                href = ""
                                +""
                            }
                        }
                    }
                }
            }

            main {
                when (content) {
                    //"home" -> this.insert(nuevoTemplate(), TemplatePlaceholder())
                    //"newFilm" -> this.insert(nuevoTemplate(), TemplatePlaceholder())
                    //"infoFilm" -> this.insert(nuevoTemplate(), TemplatePlaceholder())
                }
            }
            footer {
                a { +"production by x" }
            }
        }
    }
}