package com.elements.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ElementControllerTest {
    @Mock
    private lateinit var elementService: ElementService

    @InjectMocks
    private lateinit var elementController: ElementController

    @Test
    fun `should call the elements service`() {
        elementController.getElements()

        verify(elementService).searchElements()
    }
}