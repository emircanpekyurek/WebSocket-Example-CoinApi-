package com.pekyurek.emircan.data.repository

import android.content.Context
import com.pekyurek.emircan.R
import com.pekyurek.emircan.domain.exception.ServiceException
import com.pekyurek.emircan.domain.exception.service.FailResponseException
import com.pekyurek.emircan.domain.exception.service.NullResponseException
import com.pekyurek.emircan.domain.model.request.AssetsRequest
import com.pekyurek.emircan.domain.model.request.AssetIconsRequest
import com.pekyurek.emircan.domain.model.request.HistoricalDataRequest
import com.pekyurek.emircan.domain.model.request.SymbolRequest
import com.pekyurek.emircan.domain.model.response.AssetDetail
import com.pekyurek.emircan.domain.model.response.AssetIcon
import com.pekyurek.emircan.domain.model.response.HistoricalData
import com.pekyurek.emircan.domain.model.response.SymbolDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val context: Context,
    private val apiService: ApiService,
) : Repository {

    override fun assets(assetsRequest: AssetsRequest): Flow<ResultStatus<List<AssetDetail>>> {
        return execute { apiService.assets(assetsRequest.requestIdListModel) }
    }

    override fun symbols(symbolRequest: SymbolRequest): Flow<ResultStatus<List<SymbolDetail>>> {
        return execute { apiService.symbols(symbolRequest.assetIdRequestModel, symbolRequest.exchangeId, symbolRequest.symbolId) }
    }

    override fun assetIcons(assetIconsRequest: AssetIconsRequest): Flow<ResultStatus<List<AssetIcon>>> {
        return execute { apiService.assetIcons(assetIconsRequest.iconSize) }
    }

    override fun historicalData(historicalDataRequest: HistoricalDataRequest): Flow<ResultStatus<List<HistoricalData>>> {
        return execute { apiService.historicalData(historicalDataRequest.symbolId, historicalDataRequest.startTime, historicalDataRequest.endTime) }
    }

    private fun <T> execute(suspendResponse: suspend () -> Response<T>): Flow<ResultStatus<T>> =
        flow<ResultStatus<T>> {
            val response = suspendResponse.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ResultStatus.Success(it))
                } ?: emit(ResultStatus.Exception(NullResponseException(context)))
            } else {
                emit(
                    ResultStatus.Exception(
                        FailResponseException(
                            context,
                            response.code()
                        )
                    )
                )
            }
        }.onStart {
            emit(ResultStatus.Loading(true))
        }.catch { e ->
            emit(
                ResultStatus.Exception(
                    ServiceException(
                        e.message ?: e.localizedMessage ?: context.getString(
                            R.string.exception_service_generic_error_message
                        )
                    )
                )
            )
        }.onCompletion {
            emit(ResultStatus.Loading(false))
        }.flowOn(Dispatchers.IO)

}