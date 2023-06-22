package com.sj.nytimespopular.data.repo

import com.sj.nytimespopular.data.models.mapper.toDomainArticle
import com.sj.nytimespopular.data.network.NYTimesAPI
import com.sj.nytimespopular.domain.data.Article
import com.sj.nytimespopular.domain.data.NetworkResponse
import com.sj.nytimespopular.domain.repo.PopularArticlesImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class PopularArticleRepository @Inject constructor(private val api: NYTimesAPI) :
    PopularArticlesImpl {
    override fun getAll(): Flow<NetworkResponse<ArrayList<Article>>> = flow {
        emit(NetworkResponse.Loading)
        try {
            val articles = ArrayList<Article>()
            val articlesResponse = api.getPopularArticles()
            articlesResponse.articles.forEach { article ->
                articles.add(article.toDomainArticle())
            }
            emit(NetworkResponse.Success(articles))
        } catch (exception: IOException) {
            emit(NetworkResponse.Failed(exception.message))
        } catch (exception: HttpException) {
            val localizedMessage = exception.localizedMessage
            exception.printStackTrace()
            emit(NetworkResponse.Failed(localizedMessage))
        } catch (exception: UnknownHostException) {
            val localizedMessage = exception.localizedMessage
            exception.printStackTrace()
            emit(NetworkResponse.Failed(localizedMessage))
        }
    }
}