package com.processor.csv

import com.processor.csv.util.CSVHelper
import org.jooby.Jooby.run
import org.jooby.Kooby
import org.jooby.Results
import org.jooby.hbs.Hbs
import java.io.File
import java.net.URL

/**
 * Power up the web framework
 *
 * created by Joseph G.
 */

class App: Kooby({

  // Handlebars Module for templating
  use(Hbs())

  // Instantiates our CSV helper
  val csvHelper = CSVHelper()

  /**
   * Receives HTTP.GET requests from '/' route
   *
   * @return Home Page
   */
  get("/") {
    Results.html("home")
  }

  /**
   * Receives HTTP.GET requests from '/smart-reader' route
   *
   * @return Smart Reader Page
   */
  get("/smart-reader") {
    val fileName = "music-releases.csv"
    val classLoader = javaClass.classLoader
    val resource: URL? = classLoader.getResource(fileName)
    if (resource == null) {
      throw IllegalArgumentException("File $fileName could not found!")
    } else {
      val records = csvHelper.csvMapper(File(resource.file))
      Results.html("smart").put(mapOf("records" to records, "title" to fileName))
    }
  }

  /**
   * Receives HTTP.POST requests from '/general-reader' route
   *
   * Receives multipart/form-data
   *
   * @return General Reader Page
   */
  post("/general-reader") {
    val upload = file("file").file()
    val records = csvHelper.csvReader(upload)
    Results.html("general").put(mapOf("records" to records, "title" to upload.name))
  }

})

fun main(args: Array<String>) {
  run(::App, args)
}