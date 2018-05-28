package ru.androidpirates.aiweather.common.mock

import java.security.SecureRandom
import java.util.*

class RandomString constructor(
        length: Int = 21,
        random: Random = SecureRandom(),
        symbols: String = alphanum
) {

    private val random: Random

    private val symbols: CharArray

    private val buf: CharArray

    /**
     * Generate a random string.
     */
    fun nextString(): String {
        buf.indices.forEach { buf[it] = symbols[random.nextInt(symbols.size)] }
        return String(buf)
    }

    init {
        if (length < 1) throw IllegalArgumentException()
        if (symbols.length < 2) throw IllegalArgumentException()
        this.random = random
        this.symbols = symbols.toCharArray()
        this.buf = CharArray(length)
    }

    companion object {
        const val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        const val lower = "abcdefghijklmnopqrstuvwxyz"
        const val digits = "0123456789"
        const val alphanum = upper + lower + digits
    }
}