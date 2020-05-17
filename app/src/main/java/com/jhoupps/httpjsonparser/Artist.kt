package com.jhoupps.httpjsonparser

//This data class holds one artist object from the JSON data
data class Artist(
    val name: String,
    val smallImageURL: String,
    val largeImageURL: String
)

//Expected Data Format
/*
"name": "Linkin Park",
"smallImageURL": "https://picsum.photos/seed/LinkinPark/50",
"largeImageURL": "https://picsum.photos/seed/LinkinPark/256"
*/