package com.pekyurek.emircan.domain.model.response

import com.pekyurek.emircan.domain.model.base.BaseResponse
import com.squareup.moshi.Json

data class AssetDetail (
    @Json(name = "asset_id")
    val assetId: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "data_start")
    val dataStart: String? = null,
    @Json(name = "type_is_crypto")
    val typeIsCrypto: Int = 0,
    @Json(name = "data_end")
    val dataEnd: String? = null,
    @Json(name = "data_quote_start")
    val dataQuoteStart: String? = null,
    @Json(name = "data_quote_end")
    val dataQuoteEnd: String? = null,
    @Json(name = "data_orderbook_start")
    val dataOrderbookStart: String? = null,
    @Json(name = "data_orderbook_end")
    val dataOrderbookEnd: String? = null,
    @Json(name = "data_trade_start")
    val dataTradeStart: String? = null,
    @Json(name = "data_trade_end")
    val dataTradeEnd: String? = null,
    @Json(name = "data_symbols_count")
    val dataSymbolsCount: Int? = null,
    @Json(name = "volume_1hrs_usd")
    val volume1hrsUsd: Double? = null,
    @Json(name = "volume_1day_usd")
    val volume1dayUsd: Double? = null,
    @Json(name = "volume_1mth_usd")
    val volume1mthUsd: Double? = null,
    @Json(name = "price_usd")
    val priceUsd: Double? = null
) : BaseResponse()