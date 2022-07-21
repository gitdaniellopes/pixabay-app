package br.com.pixabayapp.di

import br.com.pixabayapp.data.source.ImageRemoteDataSourceImpl
import br.com.pixabayapp.domain.source.ImageRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindImageRemoteDataSource(dataSourceImpl: ImageRemoteDataSourceImpl): ImageRemoteDataSource
}