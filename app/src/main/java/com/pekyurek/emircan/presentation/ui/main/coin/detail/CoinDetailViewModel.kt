package com.pekyurek.emircan.presentation.ui.main.coin.detail

import androidx.lifecycle.MutableLiveData
import com.pekyurek.emircan.domain.model.request.HistoricalDataRequest
import com.pekyurek.emircan.domain.usecase.HistoricalDataUseCase
import com.pekyurek.emircan.presentation.core.extensions.toBackendFormat
import com.pekyurek.emircan.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(private val historicalDataUseCase: HistoricalDataUseCase) :
    BaseViewModel() {

    val historicalData = MutableLiveData<String>()

    fun loadData(symbolId: String) {
        val weekInMillis = TimeUnit.DAYS.toMillis(7)
        val startTime = Date().apply { time -= weekInMillis }.toBackendFormat()
        val endTime = Date().toBackendFormat()
        val historicalDataRequest = HistoricalDataRequest(symbolId, startTime, endTime)
        request(
            flow = historicalDataUseCase.execute(historicalDataRequest),
            onSuccess = { list ->
                historicalData.postValue(list.joinToString("\n") { "${it.timeCoinApi}\t\t${it.price}" })
            }
        )
    }
}