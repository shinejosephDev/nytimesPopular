package com.sj.nytimespopular.data.network

import com.sj.nytimespopular.data.models.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTimesAPI {
    @GET("mostpopular/v2/viewed/7.json")
    suspend fun getPopularArticles(@Query("api-key") apiKey: String = "mwi1ukw3256JHNbRNrS2VzAQKLsxfKQG"): ArticlesResponse
}