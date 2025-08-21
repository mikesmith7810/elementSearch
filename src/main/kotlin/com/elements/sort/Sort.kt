package com.elements.sort

data class Sort(
    val sortField: SortField,
    val sortOrder: Boolean
) {
    companion object {
        fun fromString(sort: String): Sort {
            val parts = sort.split("_")
            if (parts.size != 2) {
                throw IllegalArgumentException("Invalid sort format: $sort")
            }
            return Sort(
                sortField = SortField.fromString(parts[0]),
                sortOrder = parts[1].equals("asc", ignoreCase = true)
            )
        }
    }
}