package com.nikitamaslov.storage.model.single

import io.realm.RealmModel

internal interface Single : RealmModel {

    val id: String

    val singleKey get() = PRIMARY_KEY

    companion object {

        const val PRIMARY_KEY = "0"
    }
}