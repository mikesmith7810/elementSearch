package com.elements.controller

import com.elements.factory.ElementSearchFactory
import com.elements.model.Element
import com.elements.service.ElementService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ElementController(
    private val elementService: ElementService,
    private val elementSearchFactory: ElementSearchFactory
) {

    @GetMapping("/getElements")
    fun getElements(
        @RequestParam(name = "minDensity", required = false) minDensity: Double? = null,
        @RequestParam(name = "maxDensity", required = false) maxDensity: Double? = null,
        @RequestParam(name = "phase", required = false) phase: String? = null,
        @RequestParam(name = "sort", required = false) sort: List<String>? = null,
        @RequestParam(name = "limit", required = false) limit: Int? = null
    ): List<Element> {
        val elementSearchRequest = elementSearchFactory.createElementSearchRequestFrom(
            minDensity = minDensity,
            maxDensity = maxDensity,
            phase = phase,
            sort = sort,
            limit = limit
        );
        return elementService.searchElements(elementSearchRequest)
    }
}