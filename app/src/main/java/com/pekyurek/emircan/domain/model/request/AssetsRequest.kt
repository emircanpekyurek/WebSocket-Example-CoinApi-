package com.pekyurek.emircan.domain.model.request

import com.pekyurek.emircan.domain.constants.CoinApiConstants
import com.pekyurek.emircan.domain.model.base.BaseRequest

data class AssetsRequest(
    val assetIdList: List<String>,
) : BaseRequest() {
    val requestIdListModel : String get() = assetIdList.joinToString(CoinApiConstants.COMMA_SEPARATOR)
}