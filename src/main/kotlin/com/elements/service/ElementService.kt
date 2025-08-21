package com.elements.service

import com.elements.data.DataLoader
import com.elements.model.Element
import com.elements.model.ElementSearchRequest
import com.elements.model.Phase
import org.springframework.stereotype.Service

@Service
class ElementService(private val dataLoader: DataLoader) {
    fun searchElements(elementSearchRequest: ElementSearchRequest): List<Element> {

        val minDensityFilter: (Element) -> Boolean = { element ->
            elementSearchRequest.minDensity == null || element.density == "" || element.density.toDouble() >= elementSearchRequest.minDensity
        }
        val maxDensityFilter: (Element) -> Boolean = { element ->
            elementSearchRequest.maxDensity == null || element.density == "" || element.density.toDouble() <= elementSearchRequest.maxDensity
        }
        val phaseFilter: (Element) -> Boolean = { element ->
            elementSearchRequest.phase == Phase.UNKNOWN || element.phase == elementSearchRequest.phase
        }

        return dataLoader.getElements()
            .also { elements -> println("Stored elements : ${elements.size}") }
            .filter(minDensityFilter)
            .filter(maxDensityFilter)
            .filter(phaseFilter)
            .take(elementSearchRequest.limit ?: Int.MAX_VALUE)
            .also { elements -> println("Filtered elements : ${elements.size}") }
            .toList()
    }
}

