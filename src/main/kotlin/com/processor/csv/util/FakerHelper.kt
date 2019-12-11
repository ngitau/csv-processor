package com.processor.csv.util

import com.github.javafaker.Faker
import com.processor.csv.sdk.MusicRelease
import java.text.SimpleDateFormat

class FakerHelper {
  val faker = Faker()
  val dateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")

  fun musicReleases(size: Int): MutableList<List<String>> {
    var count = 0
    val headers = listOf("title", "main_artist", "created_at", "updated", "status")
    val list: MutableList<List<String>> = mutableListOf()

    while (count < size) {
      val release = MusicRelease(
        title = faker.rickAndMorty().location(),
        main_artist = faker.rickAndMorty().character(), //faker.artist().name(),
        created_at = dateFormat.format(faker.date().birthday()),
        updated = faker.bool().toString().toBoolean(),
        status = faker.number().numberBetween(-5, 5)

      )

      list.add(listOf(
        release.title,
        release.main_artist,
        release.created_at,
        release.updated.toString(),
        release.status.toString()
      ))
      count++
    }

    if (list.isNotEmpty()) {
      list.add(0, headers)
    }

    return list
  }
}