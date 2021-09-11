package com.pekyurek.emircan.domain.usecase

import com.pekyurek.emircan.data.repository.Repository
import com.pekyurek.emircan.data.repository.ResultStatus
import com.pekyurek.emircan.domain.model.request.AssetIconsRequest
import com.pekyurek.emircan.domain.model.response.AssetIcon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AssetIconsUseCase @Inject constructor(private val repository: Repository) :
    UseCase<AssetIconsRequest, List<AssetIcon>> {

    override fun execute(request: AssetIconsRequest): Flow<ResultStatus<List<AssetIcon>>> {
        return repository.assetIcons(request)
    }

}