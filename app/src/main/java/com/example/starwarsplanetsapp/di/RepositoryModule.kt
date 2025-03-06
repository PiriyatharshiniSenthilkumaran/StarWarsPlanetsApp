package com.example.starwarsplanetsapp.di

import com.example.starwarsplanetsapp.data.repository.PlanetRepository
import com.example.starwarsplanetsapp.data.repository.PlanetRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPlanetRepository(
        planetRepositoryImpl: PlanetRepositoryImpl
    ): PlanetRepository
}