package com.sj.nytimespopular.di

import com.sj.nytimespopular.data.network.NYTimesAPI
import com.sj.nytimespopular.data.repo.PopularArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(nyTimesApi: NYTimesAPI): PopularArticleRepository {
        return PopularArticleRepository(nyTimesApi)
    }
}