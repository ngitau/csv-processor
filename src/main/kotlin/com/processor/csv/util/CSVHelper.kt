package com.processor.csv.util

import com.processor.csv.sdk.MusicRelease
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat


/**
 * Helper class to conduct CSV Operations
 *
 * created by Joseph G.
 */

class CSVHelper() {

  /**
   * @param file CSV File object for reading
   * @return Mutable list of lists of strings
   */
  fun csvReader(file: File): MutableList<List<String>> {
    val records: MutableList<List<String>> = mutableListOf()
    BufferedReader(FileReader(file)).use { br ->
      var line: String? = null
      while (br.readLine().also { line = it } != null) {
        val values = CSVReader.parseLine(line)
        records.add(values)
      }
    }
    return records
  }

  /**
   * @param file CSV File object for reading
   * @return Mutable list of MusicRelease objects
   */
  fun csvMapper(file: File): MutableList<MusicRelease> {
    val records: MutableList<MusicRelease> = mutableListOf()
    BufferedReader(FileReader(file)).use { br ->
      var line: String? = null
      while (br.readLine().also { line = it } != null) {
        val values = CSVReader.parseLine(line)
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