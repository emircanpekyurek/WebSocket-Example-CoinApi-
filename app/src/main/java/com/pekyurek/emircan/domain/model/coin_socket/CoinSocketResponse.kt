package com.pekyurek.emircan.domain.model.coin_socket

import com.squareup.moshi.Json

data class CoinSocketResponse(
    @Json(name = "price")
    val price: Double,
    @Json(name = "symbol_id")
    val symbolId: String
)