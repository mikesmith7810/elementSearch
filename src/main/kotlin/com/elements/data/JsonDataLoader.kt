package com.elements.data

import com.elements.model.Element

class JsonDataLoader(private val dataFile: String) : DataLoader {

    override fun getElements(): List<Element> {
        return emptyList()
    }

    override fun loadElements(): List<Element> {
        TODO("Not yet implemented")
    }
}