package com.example.coinapp.repository

import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinList
import com.example.coinapp.service.CoinAPI
import com.example.coinapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CoinRepository @Inject constructor(
    private val api: CoinAPI
) {

    suspend fun getCryptoList(): Resource<CoinList> {
        val response = try {
            api.getCryptoList()
        } catch(e: Exception) {
            return Resource.Error("Error.")
        }
        return Resource.Success(response)
    }

    suspend fun getCrypto(id: String): Resource<Coin> {
        val response = try {
            api.getCrypto()
        } catch(e: Exception) {
            return Resource.Error("Error")
        }
        return Resource.Success(response)
    }
}