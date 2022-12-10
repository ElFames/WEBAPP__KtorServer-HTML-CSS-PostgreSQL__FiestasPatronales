package wm.models.city

import kotlinx.serialization.Serializable

@Serializable
data class JsonCity(
    val id: Int?,
    val name: String?
)