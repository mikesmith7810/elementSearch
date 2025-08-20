package com.elements.controller

import com.elements.data.ElementSearchRequest
import com.elements.data.Phase
import com.elements.factory.ElementSearchFactory
import com.elements.service.ElementService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

private const val GAS = "gas"
private const val NAME_ASC = "name_asc"
private const val MIN_DENSITY = 0.01
private const val MAX_DENSITY = 0.9

class ElementControllerTest {
    private val elementService = mockk<ElementService>()

    private val elementSearchFactory = mockk<ElementSearchFactory>()

    private val elementController = ElementController(elementService, elementSearchFactory)

    @Test
    fun `should call the elements service`() {

        every {
            elementSearchFactory.createElementSearchRequestFrom(
                minDensity = MIN_DENSITY,
                maxDensity = MAX_DENSITY,
                phase = GAS,
                sort = listOf(NAME_ASC),
                limit = 10
            )
        } returns ElementSearchRequest(
            minDensity = MIN_DENSITY,
            maxDensity = MAX_DENSITY,
            phase = Phase.GAS,
            sort = listOf(NAME_ASC),
            limit = 10
        )

        every {
            elementService.searchElements(
                ElementSearchRequest(
                    minDensity = MIN_DENSITY,
                    maxDensity = MAX_DENSITY,
                    phase = Phase.GAS,
                    sort = listOf(NAME_ASC),
                    limit = 10
                )
            )
        } returns listOf()


        elementController.getElements(
            minDensity = MIN_DENSITY,
            maxDensity = MAX_DENSITY,
            phase = GAS,
            sort = listOf(NAME_ASC),
            limit = 10
        )

        verify(exactly = 1) {
            elementSearchFactory.createElementSearchRequestFrom(
                minDensity = MIN_DENSITY,
                maxDensity = MAX_DENSITY,
                phase = GAS,
                sort = listOf(NAME_ASC),
                limit = 10
            )
        }

        verify(exactly = 1) {
            elementService.searchElements(
                ElementSearchRequest(
                    minDensity = MIN_DENSITY,
                    maxDensity = MAX_DENSITY,
                    phase = Phase.GAS,
                    sort = listOf(NAME_ASC),
                    limit = 10
                )
            )
        }
    }
}