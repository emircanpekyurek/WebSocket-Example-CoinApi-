package com.pekyurek.emircan.presentation.core.extensions

import java.text.SimpleDateFormat
import java.util.*

val backendDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:ss:mm'.0000000Z'") }

fun Date.toBackendFormat(): String {
    return backendDateFormat.format(this)
}