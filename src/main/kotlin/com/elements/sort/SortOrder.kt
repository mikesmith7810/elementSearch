package com.elements.sort

enum class SortOrder {
    ASC,
    DESC;

    companion object {
        fun fromString(value: String): SortOrder {
            return when (value.lowercase()) {
                "asc" -> ASC
                "desc" -> DESC
                else -> throw IllegalArgumentException("Invalid sort order: $value")
            }
        }
    }
}