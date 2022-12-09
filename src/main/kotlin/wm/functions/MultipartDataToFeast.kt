package wm.functions

import io.ktor.http.content.*
import java.lang.Exception
import java.nio.file.Path
import kotlin.io.path.writeBytes

suspend fun multipartDataToFeast(multipartData: MultiPartData): Map<String, String> {
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
                val fileName = part.originalFileName as String
                val fileBytes = part.streamProvider().readBytes()
                val path = Path.of("images/${fileName}")
                path.writeBytes(fileBytes)
                feastProperties[part.name!!] = fileName
            }
            else -> throw Exception("El formato del archivo no es válido")
        }
        part.dispose()
    }
    return feastProperties
}