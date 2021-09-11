package com.pekyurek.emircan.presentation.core.extensions

fun Double.format(digits: Int = 5) = "%.${digits}f".format(this)

