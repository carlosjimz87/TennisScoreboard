package com.carlosjimz87.tennisscoreboard.domain.models

enum class Point(val d:String, val v: Int) {
    LOVE("0", 0),
    FIFTEEN("15", 1),
    THIRTY("30", 2),
    FORTY("40", 3),
    ADVANTAGE("Adv", 4),
    DEUCE("Deu", 5);

    companion object {
        fun fromValue(value: Int): Point = entries.first { it.v == value }
    }
}