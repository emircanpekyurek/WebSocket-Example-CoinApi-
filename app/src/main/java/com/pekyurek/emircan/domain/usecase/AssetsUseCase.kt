package com.pekyurek.emircan.domain.usecase

import com.pekyurek.emircan.data.repository.Repository
import com.pekyurek.emircan.data.repository.ResultStatus
import com.pekyurek.emircan.domain.model.request.AssetsRequest
import com.pekyurek.emircan.domain.model.response.AssetDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AssetsUseCase @Inject constructor(private val repository: Repository) :
    UseCase<AssetsRequest, List<AssetDetail>> {

    override fun execute(request: AssetsRequest): Flow<ResultStatus<List<AssetDetail>>> {
        return repository.assets(request)
    }

}