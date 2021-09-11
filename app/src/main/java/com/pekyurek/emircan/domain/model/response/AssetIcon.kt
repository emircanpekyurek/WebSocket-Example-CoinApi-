package com.pekyurek.emircan.domain.model.response

import com.pekyurek.emircan.domain.model.base.BaseResponse
import com.squareup.moshi.Json

data class AssetIcon(
    @Json(name = "asset_id")
    val assetId: String? = null,
    @Json(name = "url")
    val url: String = "",
) : BaseResponse()