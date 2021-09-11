package com.pekyurek.emircan.domain.model.request

import com.pekyurek.emircan.domain.model.base.BaseRequest

data class AssetIconsRequest(
    val iconSize : Int
) : BaseRequest()