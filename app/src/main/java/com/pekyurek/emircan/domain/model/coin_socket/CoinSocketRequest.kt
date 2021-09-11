package com.pekyurek.emircan.domain.model.coin_socket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinSocketRequest(
    val type: String,
    val apikey: String,
    val heartbeat: Boolean,
    val subscribe_update_limit_ms_quote: Int,
    val subscribe_update_limit_ms_book_snapshot: Int,
    val subscribe_filter_symbol_id: List<String>,
    val subscribe_data_type: List<String>
) : Parcelable