package com.pekyurek.emircan.domain.model.response

import com.squareup.moshi.Json

data class SymbolDetail(
    @Json(name = "symbol_id")
    val symbolId: String? = null,
    @Json(name = "asset_id_base")
    val assetId: String? = null,
    @Json(name = "price")
    val price: Double? = null
)