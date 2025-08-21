package com.elements

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ElementSearchApplication

fun main(args: Array<String>) {
    runApplication<ElementSearchApplication>(*args)
}
