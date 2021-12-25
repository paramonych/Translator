package tech.dobrobot.apps.utils.extensions

import java.util.regex.Pattern

fun String?.emptyIfNull(): String {
    return this ?: ""
}

fun String?.shorten(): String {
    this.emptyIfNull().let {
        return it.substring(it.length-50, it.length)
    }
}

fun String?.detectURL(): String {
    return this?.replace(Regex("[()]"), "") ?: ""
}
