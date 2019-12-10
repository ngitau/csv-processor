package com.processor.csv.helpers

import afu.org.checkerframework.checker.igj.qual.Mutable
import com.processor.csv.sdk.MusicRelease
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class CSVHelper() {

  fun readCSV(file: File): MutableList<List<String>> {
    val records: MutableList<List<String>> = mutableListOf()
    BufferedReader(FileReader(file)).use { br ->
      var line: String? = null
      while (br.readLine().also { line = it } != null) {
        val values = CSVUtil.parseLine(line)
        records.add(values)
      }
    }
    return records
  }

  fun objectMapper(file: File): MutableList<MusicRelease> {
    val records: MutableList<MusicRelease> = mutableListOf()
    BufferedReader(FileReader(file)).use { br ->
      var line: String? = null
      while (br.readLine().also { line = it } != null) {
        val values = CSVUtil.parseLine(line)
        println("We have: $values")
        val release = MusicRelease("","", "", false, 0)
        with(release) {
          title = values[0]
          main_artist = values[1]
          created_at = values[2] //getDate(values[2])
          updated = values[3]!!.toBoolean()
          status = values[4].toInt()

          records.add(release)
        }
      }
    }
    records.removeAt(0)

    return records
  }

  private fun getDate(str: String) =
      SimpleDateFormat("MM, dd yyyy").format(SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse(str))

}