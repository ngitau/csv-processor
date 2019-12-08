package com.processor.csv

import com.processor.csv.helpers.CsvHelper
import org.jooby.Jooby.run
import org.jooby.Kooby
import org.jooby.Results
import org.jooby.hbs.Hbs


class App: Kooby({
  use(Hbs())

  val reader = CsvHelper()

  get("/") {
    Results.html("home")
  }

  post("/process") {

    val upload = file("file").file()
    val separator = param("separator").value()

//    val records = reader.read(upload, separator)

    Results.html("review")
  }

})

fun main(args: Array<String>) {
  run(::App, args)
}