package com.example.myapplication.utils

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.io.File
import java.util.concurrent.ThreadLocalRandom

object MockUtils {

    fun mockDate(): LocalDateTime {
        val minDay = LocalDate.of(1970, 1, 1).toEpochDay()
        val maxDay = LocalDate.of(2019, 6, 6).toEpochDay()
        val randomDay: Long = ThreadLocalRandom.current().nextLong(minDay, maxDay)
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(randomDay), ZoneId.of("UTC"))
    }

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }

}