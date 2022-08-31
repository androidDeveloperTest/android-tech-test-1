package com.example.data

import com.example.data.datasource.remote.*
import com.example.data.repository.MarvelManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import com.example.data.repository.MarvelManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "BASE_URL"

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModuleBinder {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: HeroRemoteRepositoryImpl): HeroRemoteRepository

    @Binds
    @Singleton
    abstract fun bindMarbelRepository(marvelRepositoryImpl: MarvelManagerImpl): MarvelManager

}

@Module
@InstallIn(SingletonComponent::class)
object DataModuleProvider {


    @Provides
    fun buildNewApiService(): ApiService {

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
