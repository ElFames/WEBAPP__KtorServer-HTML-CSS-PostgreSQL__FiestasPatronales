package wm.ui.models

import kotlinx.serialization.Serializable

@Serializable
data class JsonTown(
    val id: Int?,
    val name: String?,
    val city: JsonCity?
)