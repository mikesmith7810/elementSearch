package com.elements

import com.elements.data.DataLoader
import com.elements.data.DataLoaderFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ElementSearchApplication(@Value("\${data.file.name:}") private val dataFile: String) {

    @Bean
    fun dataLoader(): DataLoader {
        return DataLoaderFactory.createDataLoader(dataFile)
    }
}

fun main(args: Array<String>) {
    runApplication<ElementSearchApplication>(*args)
}
