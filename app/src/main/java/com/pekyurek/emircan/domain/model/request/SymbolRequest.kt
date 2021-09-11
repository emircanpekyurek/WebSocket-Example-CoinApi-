package com.pekyurek.emircan.domain.model.request

import com.pekyurek.emircan.domain.constants.CoinApiConstants

data class SymbolRequest(
    val assetIdsList: List<String>,
    val exchangeId: String = CoinApiConstants.FILTER_EXCHANGE_ID,
    val symbolId: String = CoinApiConstants.FILTER_SYMBOL_ID
) {
    val assetIdRequestModel get() = assetIdsList.joinToString(CoinApiConstants.COMMA_SEPARATOR)
}