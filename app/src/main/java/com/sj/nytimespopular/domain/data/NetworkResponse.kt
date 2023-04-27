package com.sj.nytimespopular.domain.data

sealed class NetworkResponse <out T>{
    data class Success<out T>(val data: T): NetworkResponse<T>()
    data class Failed(val message: String?): NetworkResponse<Nothing>()
    object Loading: NetworkResponse<Nothing>()
}