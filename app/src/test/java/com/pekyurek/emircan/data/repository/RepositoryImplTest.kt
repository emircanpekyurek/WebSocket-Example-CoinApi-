package com.pekyurek.emircan.data.repository

import android.content.Context
import com.pekyurek.emircan.data.repository.locale.LocaleDataSource
import com.pekyurek.emircan.domain.exception.ServiceException
import com.pekyurek.emircan.domain.exception.base.BaseException
import com.pekyurek.emircan.domain.exception.service.FailResponseException
import com.pekyurek.emircan.domain.exception.service.NullResponseException
import com.pekyurek.emircan.domain.model.request.AssetsRequest
import com.pekyurek.emircan.domain.model.response.AssetDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {
    private val localeDataSource = mockk<LocaleDataSource>()
    private val remoteDataSource = mockk<RemoteDataSource>()

    private lateinit var repositoryImpl: RepositoryImpl

    private val context: Context = mockk(relaxed = true)

    @Before
    fun setUp() {
        repositoryImpl = RepositoryImpl(localeDataSource, remoteDataSource)
    }

    @Test
    fun `assets() success case`() {
        runBlocking {
            //given
            val responseModel = listOf(AssetDetail(assetId = "1"), AssetDetail(assetId = "2"))
            val requestModel = AssetsRequest(listOf("1", "2"))

            val responseFlow = flow {
                emit(ResultStatus.Loading(show = true))
                emit(ResultStatus.Success(responseModel))
                emit(ResultStatus.Loading(show = false))
            }
            coEvery {
                remoteDataSource.assets(requestModel)
            } returns responseFlow

            //when
            val result = repositoryImpl.assets(requestModel).toList()

            //then
            assert(result[0] is ResultStatus.Loading)
            assert((result[0] as ResultStatus.Loading).show)
            assert((result[1] is ResultStatus.Success))
            assertEquals((result[1] as ResultStatus.Success).data, responseModel)
            assert(result[2] is ResultStatus.Loading)
            assert((result[2] as ResultStatus.Loading).show.not())
        }
    }

    @Test
    fun `assets() NullResponseException case`() {
        assetsFailCase(NullResponseException(context))
    }

    @Test
    fun `assets() ServiceException case`() {
        assetsFailCase(ServiceException("mock error"))
    }

    @Test
    fun `assets() FailResponseException case`() {
        assetsFailCase(FailResponseException(context, 404))
    }


    private fun assetsFailCase(exception: BaseException) {
        runBlocking {
            //given
            val requestModel = AssetsRequest(listOf("1", "2"))

            val responseFlow = flow {
                emit(ResultStatus.Loading(show = true))
                emit(ResultStatus.Exception(exception))
                emit(ResultStatus.Loading(show = false))
            }
            coEvery {
                remoteDataSource.assets(requestModel)
            } returns responseFlow

            //when
            val result = repositoryImpl.assets(requestModel).toList()

            //then
            assert(result[0] is ResultStatus.Loading)
            assert((result[0] as ResultStatus.Loading).show)
            assert(result[1] is ResultStatus.Exception)
            assert(result[1] is ResultStatus.Exception)
            assertEquals((result[1] as ResultStatus.Exception).exception.message, exception.message)

            assertEquals((result[1] as ResultStatus.Exception).exception, exception) //or

            assert(result[2] is ResultStatus.Loading)
            assert((result[2] as ResultStatus.Loading).show.not())
        }
    }
}