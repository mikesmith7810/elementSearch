package com.elements.service

import com.elements.data.DataLoader
import com.elements.model.Element
import com.elements.model.ElementSearchRequest
import org.springframework.stereotype.Service

@Service
class ElementService(private val dataLoader: DataLoader) {
    fun searchElements(elementSearchRequest: ElementSearchRequest): List<Element> {
        return dataLoader.loadElements().toList()
    }
}
