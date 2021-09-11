package com.pekyurek.emircan.data.repository

import com.pekyurek.emircan.data.repository.locale.LocaleDataSource
import com.pekyurek.emircan.domain.model.request.AssetsRequest
import com.pekyurek.emircan.domain.model.request.AssetIconsRequest
import com.pekyurek.emircan.domain.model.request.HistoricalDataRequest
import com.pekyurek.emircan.domain.model.request.SymbolRequest
import com.pekyurek.emircan.domain.model.response.AssetDetail
import com.pekyurek.emircan.domain.model.response.AssetIcon
import com.pekyurek.emircan.domain.model.response.HistoricalData
import com.pekyurek.emircan.domain.model.response.SymbolDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localeDataSource: LocaleDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun assets(assetsRequest: AssetsRequest): Flow<ResultStatus<List<AssetDetail>>> {
        return remoteDataSource.assets(assetsRequest)
    }

    override fun symbols(symbolRequest: SymbolRequest): Flow<ResultStatus<List<SymbolDetail>>> {
        return remoteDataSource.symbols(symbolRequest)
    }

    override fun assetIcons(assetIconsRequest: AssetIconsRequest): Flow<ResultStatus<List<AssetIcon>>> {
        return remoteDataSource.assetIcons(assetIconsRequest)
    }

    override fun historicalData(historicalDataRequest: HistoricalDataRequest): Flow<ResultStatus<List<HistoricalData>>> {
        return remoteDataSource.historicalData(historicalDataRequest)
    }
}