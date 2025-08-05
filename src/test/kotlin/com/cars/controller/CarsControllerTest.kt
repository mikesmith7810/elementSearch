package com.cars.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CarsControllerTest {
    @Mock
    private lateinit var carService: CarService

    @InjectMocks
    private lateinit var carController: CarsController

    @Test
    fun `should call the cars service`() {
        carController.getCars()

        verify(carService).searchCars()
    }
}