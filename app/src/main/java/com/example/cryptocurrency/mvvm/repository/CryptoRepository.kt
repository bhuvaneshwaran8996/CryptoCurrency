package com.example.cryptocurrency.mvvm.repository

import com.example.cryptocurrency.mvvm.CryptoRemoteDataSource
import com.example.cryptocurrency.mvvm.db.CryptoDao
import com.example.cryptocurrency.mvvm.di.CryptoService
import com.example.cryptocurrency.mvvm.model.CryptoModel
import com.example.cryptocurrency.mvvm.model.Data
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: CryptoRemoteDataSource,
    private val localDataSource: CryptoDao
) {
    //...
}