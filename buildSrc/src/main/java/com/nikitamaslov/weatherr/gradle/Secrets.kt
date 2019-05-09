package com.nikitamaslov.weatherr.gradle

import java.io.File
import java.io.FileInputStream
import java.util.*

object Secrets {

    private const val FILE_NAME = "api-keys.properties"
    private const val OPENWEATHERMAP_API_KEY = "OPENWEATHERMAP_API_KEY"

    val openweathermapApiKey = apiKeysProperties().getProperty(OPENWEATHERMAP_API_KEY)

    private fun apiKeysProperties(): Properties {
        val filename = FILE_NAME
        val file = File(filename)
        if (!file.exists()) {
            throw Error(
                "You need to prepare a file called $filename in the project root directory.\n" +
                        "and contain the OPENWEATHERMAP_API_KEY.\n" +
                        "the content of the file should look something like:\n\n" +
                        "(project root)$ cat $filename\n" +
                        "$OPENWEATHERMAP_API_KEY=abcde123253\n"
            )
        }
        val properties = Properties()
        properties.load(FileInputStream(file))
        return properties
    }
}