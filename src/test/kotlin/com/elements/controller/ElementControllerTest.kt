package com.elements.controller

import com.elements.factory.ElementSearchFactory
import com.elements.model.ElementSearchRequest
import com.elements.model.Phase
import com.elements.service.ElementService
import com.elements.sort.Sort
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private const val GAS = "gas"
private const val NAME_ASC = "name_asc"
private const val MIN_DENSITY = 0.01
private const val MAX_DENSITY = 0.9
private const val LIMIT = 10

class ElementControllerTest {
    private val elementService = mockk<ElementService>()

    private val elementSearchFactory = mockk<ElementSearchFactory>()

    private val elementController = ElementController(elementService, elementSearchFactory)

    @ParameterizedTest
    @MethodSource("providesParameters")
    fun `should call the elements service with diff params`(
        minDensity: Double?,
        maxDensity: Double?,
        phase: String?,
        sort: List<String>?,
        limit: Int?
    ) {

        val sortList: List<Sort>? = sort?.map { Sort.fromString(it) }

        val elementSearchRequest = ElementSearchRequest(
            minDensity = minDensity,
            maxDensity = maxDensity,
            phase = Phase.fromString(phase),
            sort = sortList,
            limit = limit

        )
        every {
            elementSearchFactory.createElementSearchRequestFrom(
                minDensity = minDensity,
                maxDensity = maxDensity,
                phase = phase,
                sort = sort,
                limit = limit
            )
        } returns elementSearchRequest

        every {
            elementService.searchElements(
                elementSearchRequest
            )
        } returns listOf()


        elementController.getElements(
            minDensity = minDensity,
            maxDensity = maxDensity,
            phase = phase,
            sort = sort,
            limit = limit
        )

        verify(exactly = 1) {
            elementSearchFactory.createElementSearchRequestFrom(
                minDensity = minDensity,
                maxDensity = maxDensity,
                phase = phase,
                sort = sort,
                limit = limit
            )
        }

        verify(exactly = 1) {
            elementService.searchElements(
                elementSearchRequest
            )
        }
    }

    companion object {
        @JvmStatic
        fun providesParameters(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(MIN_DENSITY, MAX_DENSITY, GAS, listOf(NAME_ASC), LIMIT),
                Arguments.of(null, null, "", null, null)
            )
        }
    }
}