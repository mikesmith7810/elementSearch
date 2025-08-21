package com.elements.model

import com.elements.sort.Sort

data class ElementSearchRequest(
    val minDensity: Double?,
    val maxDensity: Double?,
    val phase: Phase?,
    val sort: List<Sort>?,
    val limit: Int?
)
