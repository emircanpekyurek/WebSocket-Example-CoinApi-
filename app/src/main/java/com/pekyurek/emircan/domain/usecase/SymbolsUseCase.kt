package com.pekyurek.emircan.domain.usecase

import com.pekyurek.emircan.data.repository.Repository
import com.pekyurek.emircan.data.repository.ResultStatus
import com.pekyurek.emircan.domain.model.request.SymbolRequest
import com.pekyurek.emircan.domain.model.response.SymbolDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SymbolsUseCase @Inject constructor(private val repository: Repository) :
    UseCase<SymbolRequest, List<SymbolDetail>> {

    override fun execute(request: SymbolRequest): Flow<ResultStatus<List<SymbolDetail>>> {
        return repository.symbols(request)
    }

}