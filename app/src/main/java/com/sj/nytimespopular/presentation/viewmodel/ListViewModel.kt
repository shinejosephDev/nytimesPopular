package com.sj.nytimespopular.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sj.nytimespopular.domain.data.Article
import com.sj.nytimespopular.domain.data.NetworkResponse
import com.sj.nytimespopular.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: GetArticlesUseCase
) : ViewModel() {
    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean> get() = _loadingState
    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> get() = _errorMessage
    private val _articles: MutableLiveData<ArrayList<Article>?> = MutableLiveData()
    val articles: LiveData<ArrayList<Article>?> get() = _articles


    init {
        _loadingState.value = true
        getAllArticles()
    }

    fun getAllArticles() {
        viewModelScope.launch {
            useCase.getArticles().collect { result ->
                when (result) {
                    is NetworkResponse.Loading -> {
                        _loadingState.postValue(true)
                        _errorMessage.postValue("")
                    }
                    is NetworkResponse.Failed -> {
                        _loadingState.postValue(false)
                        _errorMessage.postValue(result.message)
                    }
                    is NetworkResponse.Success -> {
                        _articles.postValue(result.data)
                        _loadingState.postValue(false)
                        _errorMessage.postValue("")
                    }
                }
            }
        }
    }
}