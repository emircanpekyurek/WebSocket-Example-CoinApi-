package com.pekyurek.emircan.presentation.ui.main.coin.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pekyurek.emircan.domain.constants.CoinApiConstants
import com.pekyurek.emircan.domain.model.coin_socket.CoinSocketRequest
import com.pekyurek.emircan.domain.model.coin_socket.CoinSocketResponse
import com.pekyurek.emircan.domain.model.mapper.CoinDetail
import com.pekyurek.emircan.domain.model.request.AssetIconsRequest
import com.pekyurek.emircan.domain.model.request.AssetsRequest
import com.pekyurek.emircan.domain.model.request.SymbolRequest
import com.pekyurek.emircan.domain.usecase.AssetIconsUseCase
import com.pekyurek.emircan.domain.usecase.AssetsUseCase
import com.pekyurek.emircan.domain.usecase.CoinSocketUseCase
import com.pekyurek.emircan.domain.usecase.SymbolsUseCase
import com.pekyurek.emircan.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import resume
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val assetsUseCase: AssetsUseCase,
    private val symbolsUseCase: SymbolsUseCase,
    private val assetIconsUseCase: AssetIconsUseCase,
    private val coinSocketUseCase: CoinSocketUseCase
) : BaseViewModel() {

    private val iconSize = 100

    private val coinList = mutableListOf<CoinDetail>()

    val assetDetailList = MutableLiveData<List<CoinDetail>>()
    val coinUpdate = MutableLiveData<CoinSocketResponse>()

    fun loadData() {
        createCoinList()
        fillCoinList()
    }

    private fun createCoinList() {
        coinList.clear()
        CoinApiConstants.COIN_LIST.forEach { coinList.add(CoinDetail(it)) }
    }

    private fun fillCoinList() = viewModelScope.launch(Dispatchers.IO) {
        val deferredList = listOf(
            async { getIcons() },
            async { getAssets() },
            async { getSymbols() }
        )
        deferredList.awaitAll()

        assetDetailList.postValue(coinList)
    }

    private suspend fun getIcons() = suspendCoroutine<Unit> { continuation ->
        request(
            flow = assetIconsUseCase.execute(AssetIconsRequest(iconSize)),
            onSuccess = { icons ->
                coinList.map { coin ->
                    val icon = icons.firstOrNull { coin.assetId == it.assetId }
                    coin.imageUrl = icon?.url.toString()
                }
                continuation.resume()
            }
        )
    }

    private suspend fun getSymbols() = suspendCoroutine<Unit> { continuation ->
        request(
            flow = symbolsUseCase.execute(SymbolRequest(coinList.map { it.assetId })),
            onSuccess = { symbolList ->
                coinList.map { coin ->
                    val symbol = symbolList.firstOrNull { coin.assetId == it.assetId }
                    coin.symbolId = symbol?.symbolId.toString()
                }
                continuation.resume()
            }
        )
    }

    private suspend fun getAssets() = suspendCoroutine<Unit> { continuation ->
        request(
            flow = assetsUseCase.execute(AssetsRequest(coinList.map { it.assetId })),
            onSuccess = { asset ->
                coinList.map { coin ->
                    val assetDetail = asset.firstOrNull { coin.assetId == it.assetId }
                    coin.name = assetDetail?.name.toString()
                    coin.setPrice(assetDetail?.priceUsd)
                }
                continuation.resume()
            }
        )
    }

    fun connectWebSocket(symbolList: List<String>) {
        val coinRequest = CoinSocketRequest(
            type = CoinApiConstants.SOCKET_REQUEST_TYPE,
            apikey = CoinApiConstants.KEY,
            heartbeat = CoinApiConstants.SOCKET_REQUEST_HEARTBEAT,
            subscribe_update_limit_ms_quote = CoinApiConstants.SOCKET_REQUEST_UPDATE_LIMIT_MS,
            subscribe_update_limit_ms_book_snapshot = CoinApiConstants.SOCKET_REQUEST_UPDATE_LIMIT_MS,
            subscribe_filter_symbol_id = symbolList,
            subscribe_data_type = listOf(CoinApiConstants.SOCKET_REQUEST_DATA_TYPE)
        )
        request(
            flow = coinSocketUseCase.execute(coinRequest),
            onSuccess = {
                coinUpdate.postValue(it)
            }
        )
    }
}