package com.elements.data

import com.elements.model.Element

class JsonDataLoader(private val dataFile: String) : DataLoader {

    override fun loadElements(): List<Element> {
        return emptyList()
    }
}