package com.nikitamaslov.datetime.model

import com.nikitamaslov.datetime.validator.validateMonth

enum class Month(val index: Int) {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    companion object Factory
}


fun Month.Factory.fromIndex(index: Int): Month {
    validateMonth(index)
    return when (index) {
        1 -> Month.JANUARY
        2 -> Month.FEBRUARY
        3 -> Month.MARCH
        4 -> Month.APRIL
        5 -> Month.MAY
        6 -> Month.JUNE
        7 -> Month.JULY
        8 -> Month.AUGUST
        9 -> Month.SEPTEMBER
        10 -> Month.OCTOBER
        11 -> Month.NOVEMBER
        12 -> Month.DECEMBER
        else -> throw IllegalStateException()
    }
}