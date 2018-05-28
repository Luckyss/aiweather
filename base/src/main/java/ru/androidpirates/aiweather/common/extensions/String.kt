package ru.androidpirates.aiweather.common.extensions

import java.security.MessageDigest

/**
 * Use to replace the placeholders for strings that use the format "text {{placeholder}} text".
 *
 * @param substitutions key value pairs (placeholder, substitutionString)
 * @return string with substitutions applied
 */
fun String.applySubstitutions(vararg substitutions: Pair<String, String>): String {
    var source = this

    substitutions.forEach {
        source = source.replace("{{${it.first}}}", it.second, false)
    }

    return source
}

fun String?.toMD5Hash(): String {
    if (!this.isNullOrEmpty()) {
        try {
            val array = MessageDigest.getInstance("MD5").digest(this!!.toByteArray())
            val sb = StringBuilder(array.size * 2)

            array.forEach {
                sb.append(String.format("%2X", it).replace(" ", "0"))
            }

            return sb.toString()
        } catch (e: Exception) {
        }
    }
    return ""
}