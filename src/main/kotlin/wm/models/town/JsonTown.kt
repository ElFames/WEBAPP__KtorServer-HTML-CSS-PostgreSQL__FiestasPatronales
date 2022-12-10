package wm.models.town

import kotlinx.serialization.Serializable
import wm.models.city.JsonCity

@Serializable
data class JsonTown(
    val id: Int?,
    val name: String?,
    val city: JsonCity?
)