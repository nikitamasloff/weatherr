package com.nikitamaslov.api.parser

import com.nikitamaslov.api.model.DateTimeModel
import com.nikitamaslov.api.model.TemperatureModel
import com.nikitamaslov.api.model.WeatherModel
import com.nikitamaslov.api.service.response.CurrentWeatherResponse
import com.nikitamaslov.api.service.response.HourlyForecastResponse
import com.nikitamaslov.api.util.datetime.currentDateTime
import com.nikitamaslov.api.util.datetime.mapToDateTimeModel
import com.nikitamaslov.datetime.parser.DateTimeParser
import com.nikitamaslov.datetime.parser.DateTimeTemplate
import com.nikitamaslov.datetime.parser.ParsePattern
import com.nikitamaslov.datetime.parser.SeparatorTemplate
import kotlin.math.roundToInt
import com.nikitamaslov.datetime.parser.ParseException as CalendarParseException

internal interface Parser {

    fun setTemperatureUnit(temperatureUnit: TemperatureModel.Unit)

    fun parse(src: CurrentWeatherResponse): WeatherModel

    fun parse(src: HourlyForecastResponse): Set<WeatherModel>
}

internal class ParserImpl : Parser {

    private lateinit var temperatureUnit: TemperatureModel.Unit

    override fun setTemperatureUnit(temperatureUnit: TemperatureModel.Unit) {
        this.temperatureUnit = temperatureUnit
    }

    override fun parse(src: CurrentWeatherResponse): WeatherModel = with(src) {
        val body = weather.firstOrThrowParseError()
        val temperature = TemperatureModel(
            value = main.temp.roundToInt(),
            unit = temperatureUnit
        )
        val dateTime = currentDateTime()
        val type = parseWeatherType(body.id)
        val descriptor = WeatherModel.Descriptor(body.description)
        return WeatherModel(dateTime, type, temperature, descriptor)
    }

    override fun parse(src: HourlyForecastResponse): Set<WeatherModel> = with(src) {
        val accumulator: MutableSet<WeatherModel> = HashSet()
        list.forEach { item ->
            val body = item.weather.firstOrThrowParseError()
            val dateTime = parseDateTime(
                src = item.dt_txt,
                pattern = dateTimePattern
            )
            val type = parseWeatherType(body.id)
            val descriptor = WeatherModel.Descriptor(body.description)
            val temperature = TemperatureModel(
                value = item.main.temp.roundToInt(),
                unit = temperatureUnit
            )
            val weather = WeatherModel(dateTime, type, temperature, descriptor)
            accumulator.add(weather)
        }
        return accumulator.takeIf { it.isNotEmpty() } ?: parseError()
    }

    private fun parseWeatherType(src: Int): WeatherModel.Type =
        when (src) {
            200, 201, 202, 210, 211, 212, 221, 230, 231, 232 -> WeatherModel.Type.THUNDERSTORM
            300, 301, 302, 310, 311, 312, 313, 314, 321 -> WeatherModel.Type.DRIZZLE
            500, 501, 502, 503, 504, 511, 520, 521, 522, 531 -> WeatherModel.Type.RAIN
            600, 601, 602, 611, 612, 613, 615, 616, 620, 621, 622 -> WeatherModel.Type.SNOW
            701, 711, 721, 731, 741, 751, 761, 762, 771, 781 -> WeatherModel.Type.ATMOSPHERE
            801, 802, 803, 804 -> WeatherModel.Type.CLOUDS
            800 -> WeatherModel.Type.CLEAR
            else -> parseError()
        }

    /*
    * yyyy-MM-dd HH:mm:ss
    * e.g. "2014-07-23 09:00:00"
    */
    private val dateTimePattern: ParsePattern = ParsePattern.Builder()
        .append(DateTimeTemplate.yearFull)
        .append(SeparatorTemplate.string("-"))
        .append(DateTimeTemplate.monthNumber)
        .append(SeparatorTemplate.string("-"))
        .append(DateTimeTemplate.dayOfMonth)
        .append(SeparatorTemplate.string(" "))
        .append(DateTimeTemplate.hour)
        .append(SeparatorTemplate.string(":"))
        .append(DateTimeTemplate.minute)
        .append(SeparatorTemplate.string(":"))
        .append(DateTimeTemplate.seconds)
        .build()

    private fun parseDateTime(src: String, pattern: ParsePattern): DateTimeModel =
        try {
            DateTimeParser
                .parse(src, dateTimePattern)
                .mapToDateTimeModel()
        } catch (e: CalendarParseException) {
            parseError()
        }


    private fun parseError(): Nothing = throw ParseException()

    private fun <T> Collection<T>.firstOrThrowParseError(): T = firstOrNull() ?: parseError()
}