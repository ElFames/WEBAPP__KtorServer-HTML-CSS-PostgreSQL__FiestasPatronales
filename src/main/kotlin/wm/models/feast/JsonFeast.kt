package wm.models.feast

import kotlinx.serialization.Serializable
import wm.models.city.JsonCity
import wm.models.town.JsonTown

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