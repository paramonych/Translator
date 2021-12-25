package tech.dobrobot.apps.utils.extensions

fun String?.emptyIfNull(): String {
    return this ?: ""
}

fun String?.shorten(): String {
    this.emptyIfNull().let {
        return it.substring(it.length-50, it.length)
    }
}
