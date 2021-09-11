package com.pekyurek.emircan.domain.usecase

import com.pekyurek.emircan.data.repository.ResultStatus
import kotlinx.coroutines.flow.Flow

interface UseCase<Req, Res> {

    fun execute(request: Req): Flow<ResultStatus<Res>>

}