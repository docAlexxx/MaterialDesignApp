package com.example.materialdesignapp.view.recycler

const val TYPE_EARTH=1
const val TYPE_MARS=2
const val TYPE_HEADER=3
data class Data(val someText:String="title",val description:String="description",val type:Int = TYPE_EARTH)