package com.example.cryptocurrency.mvvm.db

import android.content.Context
import androidx.room.*
import com.example.cryptocurrency.mvvm.model.CryptoModel
import com.example.cryptocurrency.mvvm.model.Data

@Database(entities = [CryptoModel::class], version = 6, exportSchema = false)
@TypeConverters(CryptoDataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "Crytocurrreny")
                .fallbackToDestructiveMigration()
                .build()
    }

}