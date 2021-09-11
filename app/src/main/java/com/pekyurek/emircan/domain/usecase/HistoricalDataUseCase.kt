package com.pekyurek.emircan.domain.usecase

import com.pekyurek.emircan.data.repository.Repository
import com.pekyurek.emircan.data.repository.ResultStatus
import com.pekyurek.emircan.domain.model.request.HistoricalDataRequest
import com.pekyurek.emircan.domain.model.response.HistoricalData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoricalDataUseCase @Inject constructor(private val repository: Repository) :
    UseCase<HistoricalDataRequest, List<HistoricalData>> {

    override fun execute(request: HistoricalDataRequest): Flow<ResultStatus<List<HistoricalData>>> {
        return repository.historicalData(request)
    }

}