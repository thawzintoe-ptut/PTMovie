package com.ptut.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter

object LongArrayColumnAdapter: ColumnAdapter<List<Long>, String> {
    override fun decode(databaseValue: String): List<Long> {
        return databaseValue.split(",")
            .map { it.toLong() }
    }

    override fun encode(value: List<Long>): String {
        return value.joinToString(",")
    }

}