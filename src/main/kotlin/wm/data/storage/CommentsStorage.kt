package wm.data.storage

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import wm.ui.models.Comment
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

class CommentsStorage {
    private val path = Path.of("comments.json")
    var commentsList = mutableListOf<Comment>()

    fun loadComments() {
        if (path.exists())
            commentsList = Json.decodeFromString(path.readText())
    }

    fun saveComment(name: String, email: String, text: String) {
        val newComment = Comment(name,email,text)
        commentsList.add(newComment)
        val json = Json.encodeToString(commentsList)
        path.writeText(json)
    }
}