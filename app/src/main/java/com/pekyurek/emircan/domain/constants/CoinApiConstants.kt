package com.pekyurek.emircan.domain.constants

import java.util.concurrent.TimeUnit

object CoinApiConstants {
    const val KEY = ""
    const val WEB_SOCKET_URL = "wss://ws.coinapi.io/v1/"

    const val FILTER_EXCHANGE_ID = "BINANCE"
    const val FILTER_SYMBOL_ID = "USDT"

    val COIN_LIST = listOf("BTC", "DOGE", "ETH", "LTC", "ADA")

    const val COMMA_SEPARATOR = ","

    const val SOCKET_REQUEST_TYPE = "hello"
    const val SOCKET_REQUEST_HEARTBEAT = false
    val SOCKET_REQUEST_UPDATE_LIMIT_MS = TimeUnit.MINUTES.toMillis(5).toInt()
    const val SOCKET_REQUEST_DATA_TYPE = "trade"
}