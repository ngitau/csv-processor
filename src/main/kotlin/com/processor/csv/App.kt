package com.processor.csv

import com.processor.csv.helpers.CsvHelper
import org.jooby.Jooby.run
import org.jooby.Kooby
import org.jooby.Results
import org.jooby.hbs.Hbs


class App: Kooby({
  use(Hbs())

  get("/") {
    Results.html("home")
  }

  post("/review") {

    val upload = file("file").file()
    val separator = ","

    val records = CsvHelper(separator).reader(upload)

    Results.html("review").put(mapOf("records" to records, "title" to "Review"))
  }

})

fun main(args: Array<String>) {
  run(::App, args)
}