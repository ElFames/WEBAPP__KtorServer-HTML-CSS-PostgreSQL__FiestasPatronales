package wm.functions

import io.ktor.http.content.*
import java.lang.Exception

suspend fun multipartDataToImage(multipartData: MultiPartData): MutableList<String> {
    val feastProperties = mutableListOf<String>()
    var imageInByteArray: ByteArray

    multipartData.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> feastProperties.add(part.value)
            is PartData.FileItem-> {
                imageInByteArray = part.streamProvider().readBytes()
                feastProperties.add(imageInByteArray.toString())
            }
            else -> throw Exception("El formato del archivo no es válido")
        }
        part.dispose()
    }
    return feastProperties
}