package com.nikitamaslov.storage.model.single

import com.nikitamaslov.storage.model.def.TemperatureUnitEntity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField

internal open class SingleTemperatureUnitWrapperEntity() : RealmObject(), Single {

    @Suppress("LeakingThis")
    @PrimaryKey
    override var id: String = singleKey

    @RealmField(name = "value")
    private var _value: TemperatureUnitEntity? = null
    val value: TemperatureUnitEntity get() = _value!!

    constructor(value: TemperatureUnitEntity) : this() {
        this._value = value
    }
}