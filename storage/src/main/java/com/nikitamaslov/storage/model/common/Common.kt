package com.nikitamaslov.storage.model.common

import io.realm.RealmModel

internal interface Common : RealmModel {

    val id: String
}