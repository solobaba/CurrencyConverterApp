package com.solobaba.currencyconverter.di

import com.solobaba.currencyconverter.data.repositoryImpl.CurrencyConverterRepositoryImpl
import com.solobaba.currencyconverter.domain.repository.DomainCurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCurrencyConverterRepository(
        currencyConverterRepositoryImpl: CurrencyConverterRepositoryImpl
    ): DomainCurrencyRepository
}