package com.nikitamaslov.storage.model.common

import com.nikitamaslov.storage.model.def.TemperatureUnitEntity
import com.nikitamaslov.storage.model.def.WeatherTypeEntity
import io.realm.RealmObject
import io.realm.annotations.RealmField
import java.util.*

internal open class WeatherEntity() : RealmObject(), Common {

    override var id: String = createUniqueId()

    @RealmField(name = "year")
    private var _year: Int? = null
    val year: Int get() = this._year!!

    @RealmField(name = "month")
    private var _month: Int? = null
    val month: Int get() = _month!!

    @RealmField(name = "dayOfMonth")
    private var _dayOfMonth: Int? = null
    val dayOfMonth: Int get() = _dayOfMonth!!

    @RealmField(name = "hour")
    private var _hour: Int? = null
    val hour: Int get() = _hour!!

    @RealmField(name = "minute")
    private var _minute: Int? = null
    val minute: Int get() = _minute!!

    @RealmField(name = "type")
    private var _type: WeatherTypeEntity? = null
    val type: WeatherTypeEntity get() = _type!!

    @RealmField(name = "temperature")
    private var _temperature: Int? = null
    val temperature: Int get() = _temperature!!

    @RealmField(name = "temperatureUnit")
    private var _temperatureUnit: TemperatureUnitEntity? = null
    val temperatureUnit: TemperatureUnitEntity get() = _temperatureUnit!!

    @RealmField(name = "description")
    private var _description: String? = null
    val description: String get() = _description!!

    constructor(
        year: Int,
        month: Int,
        dayOfMonth: Int,
        hour: Int,
        minute: Int,
        type: WeatherTypeEntity,
        temperature: Int,
        temperatureUnit: TemperatureUnitEntity,
        description: String
    ) : this() {
        this._year = year
        this._month = month
        this._dayOfMonth = dayOfMonth
        this._hour = hour
        this._minute = minute
        this._type = type
        this._temperature = temperature
        this._temperatureUnit = temperatureUnit
        this._description = description
    }

    private fun createUniqueId(): String = UUID.randomUUID().toString()
}