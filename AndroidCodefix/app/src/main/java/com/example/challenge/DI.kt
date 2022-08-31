package com.example.challenge

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Named("UseCase")
    fun providesDispatcherForUseCase(): CoroutineDispatcher = Dispatchers.IO
}

