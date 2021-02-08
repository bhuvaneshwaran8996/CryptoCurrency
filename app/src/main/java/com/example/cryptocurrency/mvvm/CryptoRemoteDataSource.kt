package com.example.cryptocurrency.mvvm

import com.example.cryptocurrency.mvvm.di.CryptoService
import javax.inject.Inject

class CryptoRemoteDataSource @Inject constructor(
    private val cryptoService: CryptoService
) {

    suspend fun getCryptoData() = cryptoService.getCryptoData();

}