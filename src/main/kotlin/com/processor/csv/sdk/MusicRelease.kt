package com.processor.csv.sdk

import java.util.Date


data class MusicRelease (
    var title: String,
    var main_artist: String,
    var created_at: String,
    var updated: Boolean?,
    var status: Int?
)