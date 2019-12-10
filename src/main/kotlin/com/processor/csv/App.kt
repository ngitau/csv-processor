package com.processor.csv

import com.processor.csv.helpers.CSVHelper
import org.jooby.Jooby.run
import org.jooby.Kooby
import org.jooby.Results
import org.jooby.hbs.Hbs
import java.io.File
import java.net.URL


class App: Kooby({
  use(Hbs())

  val csvHelper = CSVHelper()

  get("/") {
    Results.html("home")
  }

  get("/smart-reader") {
    val fileName = "music-releases.csv"
    val classLoader = javaClass.classLoader
    val resource: URL? = classLoader.getResource(fileName)
    if (resource == null) {
      throw IllegalArgumentException("File $fileName could not found!")
    } else {
      val records = csvHelper.objectMapper(File(resource.file))
      Results.html("smart").put(mapOf("records" to records, "title" to fileName))
    }
  }

  post("/general-reader") {
    val upload = file("file").file()
    val records = csvHelper.readCSV(upload)
    Results.html("general").put(mapOf("records" to records, "title" to upload.name))
  }

})

fun main(args: Array<String>) {
  run(::App, args)
}