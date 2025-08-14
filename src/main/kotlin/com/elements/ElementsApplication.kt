package com.elements

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ElementsApplication

fun main(args: Array<String>) {
    runApplication<ElementsApplication>(*args)
}
