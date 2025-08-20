package com.elements.service

import com.elements.data.Element
import com.elements.data.ElementSearchRequest
import org.springframework.stereotype.Service

@Service
class ElementService {
    fun searchElements(elementSearchRequest: ElementSearchRequest): List<Element> {
        return listOf(
            Element(
                name = "Hydrogen",
                symbol = "H",
                atomicNumber = "1",
                atomicWeight = "1.008",
                density = "0.08988 g/L",
                meltingPoint = "-259.16 °C",
                boilingPoint = "-252.87 °C",
                absoluteMeltingPoint = "13.99 K",
                absoluteBoilingPoint = "20.28 K"
            ),
            Element(
                name = "Helium",
                symbol = "He",
                atomicNumber = "2",
                atomicWeight = "4.002602",
                density = "0.1786 g/L",
                meltingPoint = "-272.2 °C",
                boilingPoint = "-268.93 °C",
                absoluteMeltingPoint = "0.95 K",
                absoluteBoilingPoint = "4.22 K"
            )
        )
    }
}
