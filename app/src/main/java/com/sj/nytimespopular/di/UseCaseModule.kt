package com.sj.nytimespopular.di

import com.sj.nytimespopular.data.repo.PopularArticleRepository
import com.sj.nytimespopular.domain.usecase.GetArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllArticlesUseCase(articlesRepository: PopularArticleRepository): GetArticlesUseCase {
        return GetArticlesUseCase(articlesRepository)
    }
}