package com.ptut.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter

object IntegerArrayColumnAdapter:ColumnAdapter<List<Int>,String> {
    override fun decode(databaseValue: String): List<Int> {
        return databaseValue.substring(1, databaseValue.length - 1)
            .split(",")
            .map { it.toInt() }
    }

    override fun encode(value: List<Int>): String {
        return value.joinToString(",")
    }

}