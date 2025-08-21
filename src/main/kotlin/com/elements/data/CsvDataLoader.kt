package com.elements.data

import com.elements.model.Element
import com.elements.model.Phase

class CsvDataLoader(private val dataFile: String) : DataLoader {

    override fun loadElements(): List<Element> {
        val inputStream = javaClass.getResourceAsStream(dataFile)
        val lines = inputStream?.bufferedReader()?.readLines() ?: emptyList()

        val elements = lines.stream()
            .skip(1)
            .map { line ->
                val parts = line.split(",")
                Element(
                    name = parts[0],
                    symbol = parts[1],
                    atomicNumber = parts[2],
                    atomicWeight = parts[3],
                    density = parts[4],
                    meltingPoint = parts[5],
                    boilingPoint = parts[6],
                    phase = Phase.fromString(parts[7].uppercase()),
                    absoluteMeltingPoint = parts[8],
                    absoluteBoilingPoint = parts[9]
                )
            }
            .toList()

        return elements
    }
}