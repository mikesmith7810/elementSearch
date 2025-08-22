package com.elements.factory

import com.elements.model.Phase
import com.elements.sort.Sort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ElementSearchFactoryTest : FunSpec({
    val factory = ElementSearchFactory()

    test("createElementSearchRequestFrom returns correct request for valid input") {
        val result = factory.createElementSearchRequestFrom(
            minDensity = 1.0,
            maxDensity = 10.0,
            phase = "solid",
            sort = listOf("name_asc", "density_desc"),
            limit = 5
        )
        result.minDensity shouldBe 1.0
        result.maxDensity shouldBe 10.0
        result.phase shouldBe Phase.SOLID
        result.sort shouldBe listOf(Sort.fromString("name_asc"), Sort.fromString("density_desc"))
        result.limit shouldBe 5
    }

    test("createElementSearchRequestFrom handles nulls and defaults") {
        val result = factory.createElementSearchRequestFrom(
            minDensity = null,
            maxDensity = null,
            phase = null,
            sort = null,
            limit = null
        )
        result.minDensity shouldBe null
        result.maxDensity shouldBe null
        result.phase shouldBe Phase.UNKNOWN
        result.sort shouldBe null
        result.limit shouldBe null
    }

    test("createElementSearchRequestFrom ignores invalid sorts and duplicates") {
        val result = factory.createElementSearchRequestFrom(
            minDensity = null,
            maxDensity = null,
            phase = "liquid",
            sort = listOf("name_asc", "invalid", "NAME_ASC", "density_asc"),
            limit = null
        )
        result.sort shouldBe listOf(Sort.fromString("name_asc"), Sort.fromString("density_asc"))
    }

    test("createElementSearchRequestFrom handles phase case insensitivity") {
        val result = factory.createElementSearchRequestFrom(
            minDensity = null,
            maxDensity = null,
            phase = "GaS",
            sort = null,
            limit = null
        )
        result.phase shouldBe Phase.GAS
    }

    test("createElementSearchRequestFrom handles unknown phase") {
        shouldThrow<IllegalArgumentException> {
            factory.createElementSearchRequestFrom(
                minDensity = null,
                maxDensity = null,
                phase = "notaphase",
                sort = null,
                limit = null
            )
        }
    }
})
