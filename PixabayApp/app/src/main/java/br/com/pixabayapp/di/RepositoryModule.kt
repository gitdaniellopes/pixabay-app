package br.com.pixabayapp.di

import br.com.pixabayapp.data.repository.ImageRepositoryImpl
import br.com.pixabayapp.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindImageRepository(impl: ImageRepositoryImpl): ImageRepository
}