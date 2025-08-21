package com.elements.data

import com.elements.model.Element

interface DataLoader {
    fun getElements(): List<Element>
    fun loadElements(): List<Element>
}