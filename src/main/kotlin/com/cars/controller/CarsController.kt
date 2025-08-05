package com.cars.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CarsController(private val carService: CarService) {

    @GetMapping("/getCars")
    fun getCars(): String {
        return carService.searchCars()
    }
}