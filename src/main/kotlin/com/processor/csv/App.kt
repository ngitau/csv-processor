package com.processor.csv

import org.jooby.Jooby.run
import org.jooby.Kooby
import org.jooby.Results
import org.jooby.hbs.Hbs


class App: Kooby({
  use(Hbs())

  get("/") {
    Results.html("index")
  }

  post("/post") {
    
  }

})

fun main(args: Array<String>) {
  run(::App, args)
}