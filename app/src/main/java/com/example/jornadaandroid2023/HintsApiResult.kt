package com.example.jornadaandroid2023

data class HintsApiResult(
    val records: Array<HintsRecord>
)

data class HintsRecord(
    val id: String,
    val fields: Hint
)

data class Hint(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
)
