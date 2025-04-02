package com.example.coinapp.service

import com.example.coinapp.model.Coin
import com.example.coinapp.model.CoinList
import retrofit2.http.GET

interface CoinAPI {

    @GET("cryptolist.json")
    suspend fun getCryptoList(): CoinList

    @GET("crypto.json")
    suspend fun getCrypto(): Coin

}