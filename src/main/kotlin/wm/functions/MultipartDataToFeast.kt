package wm.functions

import io.ktor.http.content.*
import java.lang.Exception

suspend fun multipartDataToFeast(multipartData: MultiPartData): Map<String, String> {
    var imageInByteArray: ByteArray
    val feastProperties = mutableMapOf<String,String>()

    multipartData.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> {
                when (part.name) {
                    "name" -> feastProperties[part.name!!] = part.value
                    "dates" -> feastProperties[part.name!!] = part.value
                    "city" -> feastProperties[part.name!!] = part.value
                    "town" -> feastProperties[part.name!!] = part.value
                    "description" -> feastProperties[part.name!!] = part.value
                    "likes" -> feastProperties[part.name!!] = part.value
                    else -> throw Exception("Faltan campos por rellenar")
                }
            }
            is PartData.FileItem-> {
                println(part.contentType)
                imageInByteArray = part.streamProvider().readBytes()
                feastProperties[part.name!!] = imageInByteArray.toString()
            }
            else -> throw Exception("El formato del archivo no es válido")
        }
        part.dispose()
    }
    return feastProperties
}