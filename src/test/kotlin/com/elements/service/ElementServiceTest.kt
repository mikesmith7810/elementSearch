package com.elements.service

import com.elements.data.DataLoader
import com.elements.model.Element
import com.elements.model.ElementSearchRequest
import com.elements.model.Phase
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

private const val GAS = "gas"
private const val NAME_ASC = "name_asc"
private const val MIN_DENSITY = 0.01
private const val MAX_DENSITY = 0.9
private const val LIMIT = 10

class ElementServiceTest {
    private val dataLoader = mockk<DataLoader>()

    private val elementService = ElementService(dataLoader)

    @Test
    fun `should load elements from dataloader`() {

        val storedElements = listOf(
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
            )
        )

        every {
            dataLoader.getElements()
        } returns storedElements


        val elements = elementService.searchElements(
            ElementSearchRequest(
                minDensity = MIN_DENSITY,
                maxDensity = MAX_DENSITY,
                phase = Phase.GAS,
                sort = listOf(NAME_ASC),
                limit = LIMIT
            )
        )

        verify(exactly = 1) {
            dataLoader.getElements()
        }

        elements shouldHaveSize 1
        elements shouldBe storedElements
    }
}