package com.elements.model

data class Element(
    val name: String,
    val symbol: String,
    val atomicNumber: String,
    val atomicWeight: String,
    val density: String,
    val meltingPoint: String,
    val boilingPoint: String,
    val phase: Phase,
    val absoluteMeltingPoint: String,
    val absoluteBoilingPoint: String
)
