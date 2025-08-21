package com.elements.data

import com.elements.model.Element

interface DataLoader {
    fun loadElements(): List<Element>
}