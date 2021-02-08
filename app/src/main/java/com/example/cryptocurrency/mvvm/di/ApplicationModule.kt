package com.example.cryptocurrency.mvvm.di

import android.content.Context
import com.example.cryptocurrency.mvvm.CryptoRemoteDataSource
import com.example.cryptocurrency.mvvm.CryptoRepository
import com.example.cryptocurrency.mvvm.db.AppDatabase
import com.example.cryptocurrency.mvvm.db.CryptoDao
import com.example.cryptocurrency.mvvm.model.CryptoModel
import com.example.twinkletask.mvvm.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

interface CryptoService{

    @GET("v2/assets")
    suspend fun getCryptoData(): Response<CryptoModel>

}
@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {


    companion object {


        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

      /*  @Provides
        fun provideCryptoServices(retrofit: Retrofit) = retrofit.create(CryptoService::class.java)
*/
      @Provides
      fun provideGson(): Gson = GsonBuilder().create()

        @Provides
        fun provideCharacterService(retrofit: Retrofit): CryptoService = retrofit.create(CryptoService::class.java)

        @Singleton
        @Provides
        fun provideCryptoServiceRemoteDataSource(cryptoService: CryptoService) = CryptoRemoteDataSource(cryptoService)

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

        @Singleton
        @Provides
        fun provideCharacterDao(db: AppDatabase) = db.cryptoDao()

        @Singleton
        @Provides
        fun provideRepository(remoteDataSource: CryptoRemoteDataSource,
                              localDataSource: CryptoDao) =
            CryptoRepository(remoteDataSource, localDataSource)

    }
}