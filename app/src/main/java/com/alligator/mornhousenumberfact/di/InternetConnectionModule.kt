package com.alligator.mornhousenumberfact.di

import android.content.Context
import com.alligator.mornhousenumberfact.core.network.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InternetConnectionModule {

    @Singleton
    @Provides
    fun provideConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver {
        return ConnectivityObserver(context)
    }

}