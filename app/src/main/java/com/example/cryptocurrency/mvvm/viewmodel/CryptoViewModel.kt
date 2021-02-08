package com.example.cryptocurrency.mvvm.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cryptocurrency.mvvm.CryptoRepository
import com.example.cryptocurrency.mvvm.Resource
import com.example.cryptocurrency.mvvm.Status
import com.example.cryptocurrency.mvvm.model.CryptoModel
import com.example.cryptocurrency.mvvm.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext


class CryptoViewModel @ViewModelInject constructor(
    private val cryptoRepository: CryptoRepository
) : ViewModel() {

    val mutableLiveData:MutableLiveData<Resource<List<CryptoModel>>> = MutableLiveData<Resource<List<CryptoModel>>>();


    fun getCryptoData()= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data=cryptoRepository.getCryptoData()))

        }catch (exception: Exception){
            emit(Resource.error(data=null,message = exception.message?:"Error occured"))
        }
    }

    fun getCryptoDataFromDB(cryptoModel: CryptoModel?)= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data=cryptoRepository.getCryptoDataDB(cryptoModel)))

        }catch (exception: Exception){
            emit(Resource.error(data=null,message = exception.message?:"Error occured"))
        }
    }





}









