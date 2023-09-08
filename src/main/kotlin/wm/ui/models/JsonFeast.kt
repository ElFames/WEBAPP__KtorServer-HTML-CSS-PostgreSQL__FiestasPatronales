package wm.ui.models

import kotlinx.serialization.Serializable

@Serializable
data class JsonFeast(
    val id: Int?,
    val name: String?,
    val dates: String?,
    val description: String?,
    val city: JsonCity?,
    val town: JsonTown?,
    val image: String?,
    val likes: String
)