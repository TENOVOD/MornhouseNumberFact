package com.alligator.mornhousenumberfact.di





import com.alligator.mornhousenumberfact.data.repository.NumberRepositoryImpl
import com.alligator.mornhousenumberfact.domain.repository.NumberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android){

            install(Logging){
                level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    fun provideApi(client: HttpClient): NumberRepository {
        return NumberRepositoryImpl(
            client
        )
    }

}