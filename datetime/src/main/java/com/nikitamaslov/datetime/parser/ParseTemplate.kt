package com.nikitamaslov.datetime.parser

abstract class Template internal constructor(internal var value: String)

class DateTimeTemplate private constructor(value: String) : Template(value) {

    companion object Factory {

        val yearLast2 = DateTimeTemplate("yy")
        val yearFull = DateTimeTemplate("yyyy")

        val monthFullLetters = DateTimeTemplate("MMMM")
        val month3Letters = DateTimeTemplate("MMM")
        val monthNumber = DateTimeTemplate("MM")

        val dayOfMonth = DateTimeTemplate("dd")

        val hour = DateTimeTemplate("HH")

        val minute = DateTimeTemplate("mm")

        val seconds = DateTimeTemplate("ss")
    }
}

class SeparatorTemplate private constructor(value: String) : Template(value) {

    companion object Factory {

        fun string(src: String) = SeparatorTemplate("'$src'")
    }
}

operator fun Template.plus(other: Template): Template {
    this.value += other.value
    return this
}