package com.elements.data

import com.elements.model.Element
import com.elements.model.Phase
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

private const val testDataFile = "/testelementdatavalues.csv"

private val storedElements = listOf(
    Element(
        name = "Hydrogen",
        symbol = "H",
        atomicNumber = "1",
        atomicWeight = "1.008",
        density = "0.08988",
        meltingPoint = "-259.16",
        boilingPoint = "-252.87",
        phase = Phase.GAS,
        absoluteMeltingPoint = "13.99",
        absoluteBoilingPoint = "20.28"
    ),
    Element(
        name = "Helium",
        symbol = "He",
        atomicNumber = "2",
        atomicWeight = "4.002602",
        density = "0.1786",
        meltingPoint = "-272.2",
        boilingPoint = "-268.93",
        phase = Phase.GAS,
        absoluteMeltingPoint = "0.95",
        absoluteBoilingPoint = "4.22"
    )
)

private val dataFileElements = listOf(
    Element(
        name = "Beryllium",
        symbol = "Be",
        atomicNumber = "4",
        atomicWeight = "9.012182",
        density = "1848",
        meltingPoint = "1560.15",
        boilingPoint = "2743.15",
        phase = Phase.SOLID,
        absoluteMeltingPoint = "1560",
        absoluteBoilingPoint = "2743"
    ),
    Element(
        name = "Carbon",
        symbol = "C",
        atomicNumber = "6",
        atomicWeight = "12.0107",
        density = "2260",
        meltingPoint = "3823.15",
        boilingPoint = "4300.15",
        phase = Phase.SOLID,
        absoluteMeltingPoint = "3823",
        absoluteBoilingPoint = "4300"
    )
)

class CsvDataLoaderTest {

    private val csvDataLoader = CsvDataLoader(testDataFile)

    @Test
    fun `should load cached data`() {
        setStoredElementsOnDataLoader(storedElements)

        val elements = csvDataLoader.getElements()

        elements shouldBe storedElements
    }

    @Test
    fun `should load data from file when cache empty`() {

        val elements = csvDataLoader.getElements()

        elements shouldBe dataFileElements
    }

    private fun setStoredElementsOnDataLoader(storedElements: List<Element>) {
        csvDataLoader.storedElements = storedElements
    }
}