package com.nikitamaslov.api.util.datetime

import com.nikitamaslov.api.model.DateTimeModel
import com.nikitamaslov.datetime.current.currentDateTime as utilCurrentDateTime
import com.nikitamaslov.datetime.model.DateTime as UtilDateTime

internal fun UtilDateTime.mapToDateTimeModel() =
    DateTimeModel(year, month, dayOfMonth, hour, minute)

internal fun currentDateTime(): DateTimeModel = utilCurrentDateTime().mapToDateTimeModel()