package com.elements.data

enum class Phase {
    GAS, SOLID, LIQUID, UNKNOWN;

    companion object {
        fun fromString(value: String?): Phase {
            return when (value?.uppercase()) {
                "SOLID" -> SOLID
                "LIQUID" -> LIQUID
                "GAS" -> GAS
                else -> UNKNOWN
            }
        }
    }
}