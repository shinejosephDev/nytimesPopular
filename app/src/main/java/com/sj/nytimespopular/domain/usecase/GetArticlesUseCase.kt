package com.sj.nytimespopular.domain.usecase

import com.sj.nytimespopular.domain.repo.PopularArticlesImpl
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: PopularArticlesImpl
) {
    fun getArticles() = repository.getAll()
}