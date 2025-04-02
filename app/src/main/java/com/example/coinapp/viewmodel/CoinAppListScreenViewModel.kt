package com.example.coinapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.model.CoinListItem
import com.example.coinapp.repository.CoinRepository
import com.example.coinapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinAppListScreenViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    var cryptoList = mutableStateOf<List<CoinListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCryptoList = listOf<CoinListItem>()
    private var isSearchStarting = true

    init {
        loadCryptos()
    }


    fun searchCryptoList(query: String) {
        val listToSearch = if(isSearchStarting) {
            cryptoList.value
        } else {
            initialCryptoList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                cryptoList.value = initialCryptoList
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.currency.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                initialCryptoList = cryptoList.value
                isSearchStarting = false
            }
            cryptoList.value = results
        }
    }

    fun loadCryptos() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCryptoList()
            when(result) {
                is Resource.Success<*> -> {
                    val cryptoItems = result.data?.mapIndexed { index, item ->
                        CoinListItem(item.currency,item.price)
                    } as List<CoinListItem>

                    errorMessage.value = ""
                    isLoading.value = false
                    cryptoList.value += cryptoItems
                }
                is Resource.Error<*> -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading<*> -> {
                    errorMessage.value = ""
                }
            }
        }
    }
}