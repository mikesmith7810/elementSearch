package com.elements.sort

enum class SortField {
    NAME,
    DENSITY;

    companion object {
        fun fromString(value: String): SortField {
            return when (value.lowercase()) {
                "name" -> NAME
                "density" -> DENSITY
                else -> throw IllegalArgumentException("Invalid sort field: $value")
            }
        }
    }
}