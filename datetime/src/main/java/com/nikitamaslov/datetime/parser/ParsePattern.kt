package com.nikitamaslov.datetime.parser

private const val ERROR_EMPTY_PATTERN = "Empty pattern"

class ParsePattern private constructor() {

    private val _value = StringBuilder("")
    internal val value: String get() = _value.toString()

    constructor(builder: ParsePattern.(pattern: ParsePattern) -> Unit) : this() {
        builder.invoke(this, this)
        validate()
    }

    fun append(template: Template): ParsePattern {
        _value.append(template.value)
        return this
    }

    operator fun plusAssign(template: Template) {
        this.append(template)
    }

    private fun validate() {
        val src = _value.toString()
        check(src.isNotEmpty(), ::ERROR_EMPTY_PATTERN)
        check(src.isNotBlank(), ::ERROR_EMPTY_PATTERN)
    }

    class Builder {

        private val parsePattern = ParsePattern()

        fun append(template: Template): Builder {
            parsePattern.append(template)
            return this
        }

        operator fun plusAssign(template: Template) {
            this.append(template)
        }

        fun build(): ParsePattern {
            parsePattern.validate()
            return parsePattern
        }
    }
}