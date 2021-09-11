package com.pekyurek.emircan.presentation.core.extensions

import org.junit.Test

class DoubleTest {

    @Test
    fun formatTest() {
        assert(1.1.format(2) == "1.10")
        assert(1.1.format(4) == "1.1000")
        assert(1.13999.format(2) == "1.14")
        assert(1.13999.format(0) == "1")
        println(1.9132.format(0) == "2")
    }
}