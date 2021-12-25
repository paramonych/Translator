package tech.dobrobot.apps.utils.extensions

fun String?.emptyIfNull(): String {
    return this ?: ""
}

fun String?.detectURL(): String {
    return this?.replace(Regex("[()]"), "") ?: ""
}
