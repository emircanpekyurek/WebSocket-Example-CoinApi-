package com.pekyurek.emircan.presentation.core.extensions

import org.junit.Test
import java.util.*

class DateTest {

    @Test
    fun toBackendFormat() {
        //Given
        val timeLong = 1631307600000L
        val date = Date().apply { time = timeLong }

        //When
        val result = date.toBackendFormat()

        //Them
        assert("2021-09-11T00:00:00.0000000Z" == result)
    }
}