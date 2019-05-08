package com.nikitamaslov.storage.model.single

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField

internal open class SingleLocationEntity() : RealmObject(), Single {

    @Suppress("LeakingThis")
    @PrimaryKey
    override var id: String = singleKey

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

    constructor(
        latitude: Double,
        longitude: Double,
        city: String,
        country: String
    ) : this() {
        this._latitude = latitude
        this._longitude = longitude
        this._city = city
        this._country = country
    }
}