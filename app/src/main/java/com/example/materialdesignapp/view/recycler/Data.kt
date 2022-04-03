package com.example.materialdesignapp.view.recycler

const val TYPE_EARTH = 1
const val TYPE_MARS = 2
const val TYPE_HEADER = 3
const val TYPE_NOTES = 4

data class Data(
    val someText: String = "title",
    var description: String = "description",
    val type: Int = TYPE_EARTH
)