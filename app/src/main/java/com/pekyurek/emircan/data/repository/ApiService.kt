package com.pekyurek.emircan.data.repository

import com.pekyurek.emircan.domain.model.response.AssetDetail
import com.pekyurek.emircan.domain.model.response.AssetIcon
import com.pekyurek.emircan.domain.model.response.HistoricalData
import com.pekyurek.emircan.domain.model.response.SymbolDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("/v1/assets")
    suspend fun assets(
        @Query("filter_asset_id", encoded = true) assetIds: String,
    ): Response<List<AssetDetail>>

    @GET("/v1/symbols")
    suspend fun symbols(
        @Query("filter_asset_id", encoded = true) assetIds: String,
        @Query("filter_exchange_id") exchangeId: String,
        @Query("filter_symbol_id") symbolId: String,
    ): Response<List<SymbolDetail>>

    @GET("/v1/assets/icons/{iconSize}")
    suspend fun assetIcons(@Path("iconSize") iconSize: Int): Response<List<AssetIcon>>

    @GET("v1/trades/{symbol_id}/history")
    suspend fun historicalData(
        @Path("symbol_id") symbolId: String,
        @Query("time_start") startTime: String,
        @Query("time_end") endTime: String,
    ): Response<List<HistoricalData>>

}