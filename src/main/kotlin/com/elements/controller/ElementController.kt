package com.elements.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ElementController(private val elementService: ElementService) {

    @GetMapping("/getElements")
    fun getElements(
        @RequestParam(name = "minDensity", required = false) minDensity: Double?,
        @RequestParam(name = "maxDensity", required = false) maxDensity: Double?,
        @RequestParam(name = "phase", required = false) phase: String?,
        @RequestParam(name = "sort", required = false) sort: List<String>?,
        @RequestParam(name = "limit", required = false) limit: Int?
    ): String {
        return elementService.searchElements()
    }
}