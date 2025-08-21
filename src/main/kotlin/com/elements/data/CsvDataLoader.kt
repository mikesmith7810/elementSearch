package com.elements.data

import com.elements.model.Element
import com.elements.model.Phase

class CsvDataLoader(private val dataFile: String) : DataLoader {
    var storedElements: List<Element> = emptyList()

    override fun getElements(): List<Element> {

        if (storedElements.isNotEmpty()) {
            return storedElements
        }
        return loadElements()
    }

    override fun loadElements(): List<Element> {
        val inputStream = javaClass.getResourceAsStream(dataFile)
        val lines = inputStream?.bufferedReader()?.readLines() ?: emptyList()

        storedElements = lines.stream()
            .skip(1)
            .map { line ->
                val parts = line.split(",")
                Element(
                    name = parts[0].trim('"'),
                    symbol = parts[1].trim('"'),
                    atomicNumber = parts[2],
                    atomicWeight = parts[3],
                    density = parts[4],
                    meltingPoint = parts[5],
                    boilingPoint = parts[6],
                    phase = Phase.fromString(parts[7].trim('"').uppercase()),
                    absoluteMeltingPoint = parts[8],
                    absoluteBoilingPoint = parts[9]
                )
            }
            .toList()

        return storedElements
    }
}