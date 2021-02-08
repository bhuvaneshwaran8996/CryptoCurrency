package com.example.cryptocurrency.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cryptocurrency.mvvm.model.CryptoModel
import com.example.cryptocurrency.mvvm.model.Data

@Dao
interface CryptoDao {
    @Query("SELECT * FROM CryptoModel")
    fun getAllCryptoData() : List<CryptoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoData(cryptoModel: CryptoModel?)

    @Delete
    suspend fun delete(cryptoModel: CryptoModel?);
   /
}