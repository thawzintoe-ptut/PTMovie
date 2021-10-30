package com.ptut.cache.columnadapter

import com.squareup.sqldelight.ColumnAdapter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object LocalDateColumnAdapter : ColumnAdapter<LocalDate, String> {

  private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

  override fun decode(databaseValue: String): LocalDate {
    return LocalDate.parse(databaseValue, dateTimeFormatter)
  }

  override fun encode(value: LocalDate): String {
    return value.format(dateTimeFormatter)
  }

}
