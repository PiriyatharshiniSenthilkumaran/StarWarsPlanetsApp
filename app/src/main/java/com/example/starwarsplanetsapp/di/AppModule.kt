package com.example.starwarsplanetsapp.di

import android.content.Context
import com.example.starwarsplanetsapp.data.local.AppDatabase
import com.example.starwarsplanetsapp.data.remote.PlanetApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun providePlanetApiService(retrofit: Retrofit): PlanetApiService {
            return retrofit.create(PlanetApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
            return AppDatabase.create(context)
        }
    }
}