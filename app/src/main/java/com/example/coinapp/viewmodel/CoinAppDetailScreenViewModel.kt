package com.example.coinapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.coinapp.model.Coin
import com.example.coinapp.repository.CoinRepository
import com.example.coinapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinAppDetailScreenViewModel  @Inject constructor(
        private val repository: CoinRepository
    ) : ViewModel() {

        suspend fun getCrypto(id: String): Resource<Coin> {
            return repository.getCrypto(id)
        }
    }
