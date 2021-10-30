package com.ptut.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

object LocalTimeColumnAdapter : ColumnAdapter<LocalTime, String> {

  private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME

  override fun decode(databaseValue: String): LocalTime {
    return LocalTime.parse(databaseValue, dateTimeFormatter);
  }

  override fun encode(value: LocalTime): String {
    return value.format(dateTimeFormatter)
  }

}
