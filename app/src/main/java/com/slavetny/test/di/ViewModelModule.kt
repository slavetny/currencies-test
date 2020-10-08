package com.slavetny.test.di

import com.slavetny.test.data.repository.CurrenciesRepository
import com.slavetny.test.data.repository.CurrenciesRepositoryImpl
import com.slavetny.test.presentation.screen.allcurrencies.AllCurrenciesViewModel
import com.slavetny.test.presentation.screen.mycurrencies.MyCurrenciesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AllCurrenciesViewModel(get()) }
    viewModel { MyCurrenciesViewModel(get()) }

    single { CurrenciesRepositoryImpl(get(), get()) as CurrenciesRepository }
}