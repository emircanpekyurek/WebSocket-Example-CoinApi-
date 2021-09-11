package com.pekyurek.emircan.domain.model.mapper

import android.os.Parcelable
import com.pekyurek.emircan.presentation.core.extensions.format
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinDetail(
    val assetId: String,
    var symbolId: String = "",
    var name: String = "",
    var price: String = "",
    var imageUrl: String = "",
) : Parcelable {
    val imageTransitionName get() = "anim_${assetId}_$symbolId"

    fun setPrice(price: Double?) {
        this.price = price?.format(5).toString()
    }
}