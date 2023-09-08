package wm.ui.models

import kotlinx.serialization.Serializable

@Serializable
data class Comment(val name: String, val email: String, val text: String) {
}