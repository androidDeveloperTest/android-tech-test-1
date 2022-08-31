package com.example.presentation.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object DI {

    @Provides
    @Named("ViewModel")
    fun provideViewModelDispatcher(): CoroutineDispatcher = Dispatchers.IO
}