package com.elements.service

import com.elements.data.DataLoader
import com.elements.model.Element
import com.elements.model.ElementSearchRequest
import com.elements.model.Phase
import org.springframework.stereotype.Service

private const val NAME = "name"
private const val DENSITY = "density"

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
            .asSequence()
            .filter(minDensityFilter)
            .filter(maxDensityFilter)
            .filter(phaseFilter)
            .sortedWith(getElementComparator(elementSearchRequest))
            .take(elementSearchRequest.limit ?: Int.MAX_VALUE)
            .toList()
            .also { elements -> println("Filtered elements : ${elements.size}") }
            .toList()
    }

    private fun getElementComparator(elementSearchRequest: ElementSearchRequest): Comparator<Element> {
        val elementComparator = elementSearchRequest.sort
            ?.map { sort ->
                when (sort.sortField.name.lowercase()) {
                    NAME -> if (sort.sortOrder) compareBy { sortField -> sortField.name }
                    else compareByDescending { sortField -> sortField.name }

                    DENSITY -> if (sort.sortOrder) compareBy { sortField -> sortField.density.toDoubleOrNull() }
                    else compareByDescending { sortField -> sortField.density.toDoubleOrNull() }

                    else -> compareBy<Element> { sortField -> sortField.name }
                }
            }
            ?.reduce { accumulator, comparator -> accumulator.then(comparator) }
            ?: compareBy { sortField -> sortField.name }
        return elementComparator
    }
}

