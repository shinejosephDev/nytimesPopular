package com.sj.nytimespopular.domain.repo

import com.sj.nytimespopular.domain.data.Article
import com.sj.nytimespopular.domain.data.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface PopularArticlesImpl {
    suspend fun getAll(): Flow<NetworkResponse<ArrayList<Article>>>
}