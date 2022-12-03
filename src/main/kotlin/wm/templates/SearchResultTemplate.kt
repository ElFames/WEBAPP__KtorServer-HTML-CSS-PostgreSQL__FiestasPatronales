package wm.templates

import io.ktor.server.html.*
import kotlinx.html.*
import wm.models.Feast

class SearchResultTemplate(result: List<Feast>) : Template<FlowContent> {
    var searchResult: List<Feast> = result
    override fun FlowContent.apply() {
        div("search-results") {
            id = "results"
            div {
                searchResult.forEachIndexed { index, feast ->
                    div {
                        id = "result-$index"
                        p { text(feast.name) }
                        onClick = "window.location.href = '/fiestaspatronales/${feast.id}'"
                    }

                }
            }
        }
    }
}

