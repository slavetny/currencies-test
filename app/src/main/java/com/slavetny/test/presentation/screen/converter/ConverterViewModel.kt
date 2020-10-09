package com.slavetny.test.presentation.screen.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavetny.test.data.repository.CurrenciesRepository
import com.slavetny.test.domain.model.Convert
import kotlinx.coroutines.launch

class ConverterViewModel(
    private val repository: CurrenciesRepository
) : ViewModel() {

    private val _convertedCurrencyLiveData = MutableLiveData<Convert>()
    val convertedCurrencyLiveData: LiveData<Convert>
        get() = _convertedCurrencyLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    fun getConvertedValue(currencyFrom: String, currencyTo: String, amount: Int) {
        viewModelScope.launch {
            runCatching {
                repository.getConvertedCurrency(currencyFrom, currencyTo, amount)
            }.onSuccess {
                _convertedCurrencyLiveData.value = it.value
            }.onFailure {
                _errorLiveData.value = it.message
            }
        }
    }
}