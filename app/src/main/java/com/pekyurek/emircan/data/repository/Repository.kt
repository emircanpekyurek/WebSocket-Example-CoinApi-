package com.pekyurek.emircan.data.repository

import com.pekyurek.emircan.domain.model.request.AssetsRequest
import com.pekyurek.emircan.domain.model.request.AssetIconsRequest
import com.pekyurek.emircan.domain.model.request.HistoricalDataRequest
import com.pekyurek.emircan.domain.model.request.SymbolRequest
import com.pekyurek.emircan.domain.model.response.AssetDetail
import com.pekyurek.emircan.domain.model.response.AssetIcon
import com.pekyurek.emircan.domain.model.response.HistoricalData
import com.pekyurek.emircan.domain.model.response.SymbolDetail
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun assets(assetsRequest: AssetsRequest): Flow<ResultStatus<List<AssetDetail>>>

    fun symbols(symbolRequest: SymbolRequest): Flow<ResultStatus<List<SymbolDetail>>>

    fun assetIcons(assetIconsRequest: AssetIconsRequest): Flow<ResultStatus<List<AssetIcon>>>

    fun historicalData(historicalDataRequest: HistoricalDataRequest): Flow<ResultStatus<List<HistoricalData>>>
}