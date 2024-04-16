package wm.core.utils

fun getParams(textForm: String): MutableMap<String, String> {
    val params = mutableMapOf<String,String>()
    var name: String
    var value: String
    textForm.reader().forEachLine {
        name = it.split('=')[0]
        value = it.split('=')[1]
        params[name] = value
    }
    return params
}