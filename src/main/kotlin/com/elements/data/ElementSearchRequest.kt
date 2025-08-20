package com.elements.data

data class ElementSearchRequest(
    val minDensity: Double?,
    val maxDensity: Double?,
    val phase: Phase?,
    val sort: List<String>?,
    val limit: Int?
)
