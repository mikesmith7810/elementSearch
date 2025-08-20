package com.elements.factory

import com.elements.data.ElementSearchRequest;
import com.elements.data.Phase
import org.springframework.stereotype.Component

@Component
class ElementSearchFactory {
    fun createElementSearchRequestFrom(
        minDensity: Double?,
        maxDensity: Double?,
        phase: String?,
        sort: List<String>?,
        limit: Int?
    ): ElementSearchRequest {
        return ElementSearchRequest(
            parseMinDensity(minDensity),
            parseMaxDensity(maxDensity),
            parsePhase(phase),
            parseSort(sort),
            parseLimit(limit)
        )
    }

    private fun parseMinDensity(minDensity: Double?): Double? {
        return minDensity;
    }

    private fun parseMaxDensity(maxDensity: Double?): Double? {
        return maxDensity;
    }

    private fun parsePhase(phase: String?): Phase {
        return Phase.valueOf(phase?.uppercase() ?: Phase.UNKNOWN.name);
    }

    private fun parseLimit(limit: Int?): Int? {
        return limit;
    }

    private fun parseSort(sort: List<String>?): List<String>? {
        return sort;
    }
}