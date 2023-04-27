package com.sj.nytimespopular.presentation


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.sj.nytimespopular.domain.data.NetworkResponse
import com.sj.nytimespopular.domain.usecase.GetArticlesUseCase
import com.sj.nytimespopular.presentation.viewmodel.ListViewModel
import com.sj.nytimespopular.resources.ArticlesTestData
import com.sj.nytimespopular.util.TestCoroutineRule
import com.sj.nytimespopular.util.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


@ExperimentalCoroutinesApi
class ArticlesViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    var testCoroutineDispatcher = TestCoroutineRule()


    private lateinit var articlesViewModel: ListViewModel
    private var getAllArticlesUseCase: GetArticlesUseCase =
        mock(GetArticlesUseCase::class.java)


    @Before
    fun setUp() {

    }


    @Test
    fun `get Articles should Return Error`() = runBlockingTest {

        `when`(getAllArticlesUseCase.getArticles()).thenReturn(
            flow {
                emit(NetworkResponse.Failed("Error"))
            }
        )


        articlesViewModel = ListViewModel(getAllArticlesUseCase)
        articlesViewModel.getAllArticles()

        val error = articlesViewModel.errorMessage.getOrAwaitValueTest()
        assertThat(error).isEqualTo("Error")


    }


    @Test
    fun `get articles should succeed after articles are returned`() = runBlockingTest {


        `when`(getAllArticlesUseCase.getArticles()).thenReturn(
            flow {
                emit(NetworkResponse.Success(ArticlesTestData.articels))
            }
        )


        articlesViewModel = ListViewModel(getAllArticlesUseCase)
        articlesViewModel.getAllArticles()

        val articles = articlesViewModel.articles.getOrAwaitValueTest()

        assertThat(articles).contains(ArticlesTestData.articels[0])


    }

    @Test
    fun `get articles should return loading`() = runBlockingTest {

        `when`(getAllArticlesUseCase.getArticles()).thenReturn(
            flow {
                emit(NetworkResponse.Loading)
            }
        )


        articlesViewModel = ListViewModel(getAllArticlesUseCase)
        articlesViewModel.getAllArticles()

        val isLoading = articlesViewModel.loadingState.getOrAwaitValueTest()

        assertThat(isLoading).isTrue()


    }


}