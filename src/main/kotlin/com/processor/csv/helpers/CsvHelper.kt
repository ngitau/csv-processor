package com.processor.csv.helpers

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.*
import java.util.stream.Collectors


class CsvHelper(private  val separator: String) {

  private var contentMaps: ArrayList<Map<String, ArrayList<String>>>? = null
  private var headerNames: ArrayList<String>? = null

  fun reader(file: File): ArrayList<Map<String, ArrayList<String>>> {

    //open file and use bufferedReader to read content
    val bufferedReader = BufferedReader(FileReader(file))

    //get the first row which contains headers
    val list = bufferedReader.lines().limit(1).collect(Collectors.toList())
    contentMaps = getHeaders(list.first())

    //read each line
    bufferedReader.useLines { lines ->

      //iterate through each line
      lines.forEach { line ->
        //split the string by provided delimiter
        val cellItems = line.split(separator.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        //split data by columns and write to the array of maps
        for ((index, value) in cellItems.withIndex()) {
          contentMaps!![index][headerNames!![index]]?.add(value)
        }
      }
    }

    return contentMaps!!
  }

  private fun getHeaders(headerStream: String): ArrayList<Map<String, ArrayList<String>>> {
    val headers = headerStream.split(separator.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

    headerNames = ArrayList()
    val items = ArrayList<Map<String, ArrayList<String>>>()

    for (header in headers) {
      headerNames!!.add(header)
      items.add(mapOf(header to ArrayList()))
    }

    return items
  }
}