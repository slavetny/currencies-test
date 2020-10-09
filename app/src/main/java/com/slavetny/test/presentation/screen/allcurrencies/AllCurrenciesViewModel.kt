package com.slavetny.test.presentation.screen.allcurrencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavetny.test.data.db.CurrenciesEntity
import com.slavetny.test.data.repository.CurrenciesRepository
import com.slavetny.test.domain.model.Currencies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllCurrenciesViewModel(
    private val currenciesRepository: CurrenciesRepository
) : ViewModel() {

    private val _currenciesLiveData = MutableLiveData<Currencies>()
    val currenciesLiveData: LiveData<Currencies>
        get() = _currenciesLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    init {
        viewModelScope.launch {
            runCatching {
                currenciesRepository.getCurrencies()
            }.onSuccess {
                _currenciesLiveData.value = it.value
            }.onFailure {
                _errorLiveData.value = it.message
            }
        }
    }

    fun insertCurrency(name: String) {
        val currencies = CurrenciesEntity()
        currencies.name = name

        viewModelScope.launch(Dispatchers.IO) {
            currenciesRepository.addCurrencyInFavorite(currencies)
        }
    }
}