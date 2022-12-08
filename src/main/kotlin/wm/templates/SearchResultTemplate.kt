package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.models.Feast

class SearchResultTemplate(private val searchResult: MutableList<Feast>) : Template<FlowContent> {
    override fun FlowContent.apply() {
        div("search-results") {
            id = "results"
            div {
                searchResult.forEachIndexed { index, feast ->
                    div {
                        id = "result-$index"
                        p {
                            a(classes="enlace"){
                                href = "${feast.id}"
                                +feast.name
                            }
                        }
                    }
                }
            }
        }
    }
}