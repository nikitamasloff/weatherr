package com.nikitamaslov.storage.model.single

import com.nikitamaslov.storage.model.common.WeatherEntity
import com.nikitamaslov.storage.model.def.TemperatureUnitEntity
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField

internal open class SingleForecastEntity() : RealmObject(), Single {

    @Suppress("LeakingThis")
    @PrimaryKey
    override var id: String = singleKey

    @RealmField(name = "currentWeather")
    private var _currentWeather: WeatherEntity? = null
    val currentWeather: WeatherEntity get() = _currentWeather!!

    @RealmField(name = "futureWeather")
    private var _futureWeather = RealmList<WeatherEntity>()
    val futureWeather: Set<WeatherEntity> get() = _futureWeather.toSet()

    @RealmField(name = "latitude")
    private var _latitude: Double? = null
    val latitude: Double get() = _latitude!!

    @RealmField(name = "longitude")
    private var _longitude: Double? = null
    val longitude: Double get() = _longitude!!

    @RealmField(name = "city")
    private var _city: String? = null
    val city: String get() = _city!!

    @RealmField(name = "country")
    private var _country: String? = null
    val country: String get() = _country!!

    @RealmField(name = "temperatureUnit")
    private var _temperatureUnit: TemperatureUnitEntity? = null
    val temperatureUnit: TemperatureUnitEntity get() = _temperatureUnit!!

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

    constructor(
        currentWeather: WeatherEntity,
        futureWeather: Set<WeatherEntity>,
        latitude: Double,
        longitude: Double,
        city: String,
        country: String,
        temperatureUnit: TemperatureUnitEntity,
        year: Int,
        month: Int,
        dayOfMonth: Int,
        hour: Int,
        minute: Int
    ) : this() {
        this._currentWeather = currentWeather
        this._futureWeather.addAll(futureWeather)
        this._latitude = latitude
        this._longitude = longitude
        this._city = city
        this._country = country
        this._temperatureUnit = temperatureUnit
        this._year = year
        this._month = month
        this._dayOfMonth = dayOfMonth
        this._hour = hour
        this._minute = minute
    }
}