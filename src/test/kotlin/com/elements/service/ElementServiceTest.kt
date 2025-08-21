package com.elements.service

import com.elements.data.DataLoader
import com.elements.model.Element
import com.elements.model.ElementSearchRequest
import com.elements.model.Phase
import com.elements.sort.Sort
import com.elements.sort.SortField
import com.elements.sort.SortOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

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
                sort = listOf(Sort(SortField.NAME, SortOrder.ASC)),
                limit = LIMIT
            )
        )

        verify(exactly = 1) {
            dataLoader.getElements()
        }

        elements shouldHaveSize 1
        elements shouldBe storedElements
    }

    @ParameterizedTest
    @MethodSource("providesFilteredSearches")
    fun `should return list when elements match criteria`(
        elementSearchRequest: ElementSearchRequest, numberOfResults: Int
    ) {
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
            ),
            Element(
                name = "Carbon",
                symbol = "C",
                atomicNumber = "5",
                atomicWeight = "4.008",
                density = "6.08988",
                meltingPoint = "-459.16",
                boilingPoint = "-252.87",
                phase = Phase.GAS,
                absoluteMeltingPoint = "11.99",
                absoluteBoilingPoint = "25.28"
            ),
            Element(
                name = "Krypton",
                symbol = "K",
                atomicNumber = "23",
                atomicWeight = "4.008",
                density = "2.08988",
                meltingPoint = "-269.16",
                boilingPoint = "-152.87",
                phase = Phase.SOLID,
                absoluteMeltingPoint = "16.99",
                absoluteBoilingPoint = "29.28"
            )
        )

        every {
            dataLoader.getElements()
        } returns storedElements

        val elements = elementService.searchElements(
            elementSearchRequest
        )

        elements shouldHaveSize numberOfResults
    }

    companion object {
        @JvmStatic
        fun providesFilteredSearches(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    ElementSearchRequest(
                        minDensity = null,
                        maxDensity = null,
                        phase = Phase.UNKNOWN,
                        sort = null,
                        limit = null
                    ), 3
                ),
                Arguments.of(
                    ElementSearchRequest(
                        minDensity = 5.0,
                        maxDensity = null,
                        phase = Phase.UNKNOWN,
                        sort = null,
                        limit = null
                    ), 1
                ),
                Arguments.of(
                    ElementSearchRequest(
                        minDensity = 1.0,
                        maxDensity = 8.0,
                        phase = Phase.UNKNOWN,
                        sort = null,
                        limit = null
                    ), 2
                ),
                Arguments.of(
                    ElementSearchRequest(
                        minDensity = null,
                        maxDensity = null,
                        phase = Phase.GAS,
                        sort = null,
                        limit = null
                    ), 2
                ),
                Arguments.of(
                    ElementSearchRequest(
                        minDensity = null,
                        maxDensity = null,
                        phase = Phase.UNKNOWN,
                        sort = null,
                        limit = 1
                    ), 1
                )
            )
        }
    }
}