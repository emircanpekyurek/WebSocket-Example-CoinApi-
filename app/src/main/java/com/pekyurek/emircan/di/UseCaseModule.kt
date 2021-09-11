package com.pekyurek.emircan.di

import com.pekyurek.emircan.data.repository.Repository
import com.pekyurek.emircan.domain.constants.CoinApiConstants
import com.pekyurek.emircan.domain.usecase.*
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideAssetsUseCase(repository: Repository) = AssetsUseCase(repository)

    @Provides
    fun provideSymbolsIconsUseCase(repository: Repository) = SymbolsUseCase(repository)

    @Provides
    fun provideAssetIconsUseCase(repository: Repository) = AssetIconsUseCase(repository)

    @Provides
    fun provideCoinSocketUseCase(moshi: Moshi) = CoinSocketUseCase(moshi, CoinApiConstants.WEB_SOCKET_URL)

    @Provides
    fun provideHistoricalDataUseCase(repository: Repository) = HistoricalDataUseCase(repository)

}