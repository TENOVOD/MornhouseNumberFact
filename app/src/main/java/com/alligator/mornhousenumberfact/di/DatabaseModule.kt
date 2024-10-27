package com.alligator.mornhousenumberfact.di

import android.content.Context
import androidx.room.Room
import com.alligator.mornhousenumberfact.data.local.MainDb
import com.alligator.mornhousenumberfact.data.local.dao.NumbersDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideMainDb(
        @ApplicationContext context: Context
    ): MainDb {
        return Room.databaseBuilder(
            context,
            MainDb::class.java,
            "numbers.db"
        ).build()
    }
    @Provides
    fun provideNumbersDao(mainDb: MainDb): NumbersDao {
        return mainDb.numbersDao()
    }
}