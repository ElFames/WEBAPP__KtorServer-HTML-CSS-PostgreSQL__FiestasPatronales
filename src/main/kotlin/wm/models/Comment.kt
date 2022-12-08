package wm.models

import kotlinx.serialization.Serializable

@Serializable
data class Comment(val email: String, val text: String) {
}