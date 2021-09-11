package com.pekyurek.emircan.domain.model.request

import com.pekyurek.emircan.domain.model.base.BaseRequest

data class HistoricalDataRequest(
    val symbolId: String,
    val startTime: String,
    val endTime: String
) : BaseRequest()