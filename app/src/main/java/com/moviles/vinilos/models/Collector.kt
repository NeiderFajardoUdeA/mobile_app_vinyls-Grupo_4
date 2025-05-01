package com.moviles.vinilos.models

data class Collector (
    val collectorId : Int,
    val name : String,
    val telephone: String,
    val email: String,
    val photo: String = "https://www.pngall.com/wp-content/uploads/5/Avatar-Profile-PNG-Clipart.png"
)