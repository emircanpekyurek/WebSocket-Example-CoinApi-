package com.pekyurek.emircan.domain.model.response

import com.pekyurek.emircan.domain.model.base.BaseResponse
import com.squareup.moshi.Json

data class HistoricalData(
    @Json(name = "price") val price: Double,
    @Json(name = "time_coinapi") val timeCoinApi: String
) : BaseResponse()