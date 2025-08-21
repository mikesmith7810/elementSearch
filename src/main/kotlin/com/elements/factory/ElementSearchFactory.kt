package com.elements.factory

import com.elements.model.ElementSearchRequest;
import com.elements.model.Phase
import com.elements.sort.Sort
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

    private fun parseSort(sort: List<String>?): List<Sort>? {
        return sort
            ?.map { it.lowercase() }
            ?.filter { it == "name_asc" || it == "name_desc" || it == "density_asc" || it == "density_desc" }
            ?.distinct()
            ?.map { Sort.fromString(it) }
    }
}