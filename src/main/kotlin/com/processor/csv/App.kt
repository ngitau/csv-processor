package com.processor.csv

import org.jooby.Jooby.*
import org.jooby.Kooby

class App: Kooby({

  assets("/assets/**");
  get("/") {
    "Hello CSV!"
  }
})

fun main(args: Array<String>) {
  run(::App, args)
}