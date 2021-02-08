package com.example.cryptocurrency.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.cryptocurrency.mvvm.db.CryptoDao
import com.example.cryptocurrency.mvvm.model.CryptoModel
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val remoteDataSource: CryptoRemoteDataSource,
    private val localDataSource: CryptoDao
) {



   suspend fun getCryptoData(): Response<CryptoModel> {

        return remoteDataSource.getCryptoData();
    }

    suspend fun getCryptoDataDB(cryptoModel: CryptoModel?): List<CryptoModel> {
        localDataSource.insertCryptoData(cryptoModel);
        val cryptoLiveData:List<CryptoModel> = localDataSource.getAllCryptoData()
        localDataSource.delete(cryptoModel);
         return cryptoLiveData;
    }


}