package com.sj.nytimespopular.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sj.nytimespopular.domain.data.Article
import com.sj.nytimespopular.domain.data.NetworkResponse
import com.sj.nytimespopular.resources.FakeArticlesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticlesRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    lateinit var fakeArticlesRepository: FakeArticlesRepository


    @Before
    fun setUp() {
        fakeArticlesRepository = FakeArticlesRepository()
    }

    @Test
    fun `request to get data from api succeeds`() = runTest {

        fakeArticlesRepository.getAll().test {

            val result = awaitItem() as NetworkResponse.Success

            val articles = result.data

            val article = Article(
                id = 1,
                title = "TestTitle1",
                abstract = "TestAbstract",
                url = "https://www.nytimes.com/2022/03/09/world/europe/ukraine-family-perebyinis-kyiv.html",
                smallThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
                mediumThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
                largeThumbnail = "https://static01.nyt.com/images/2022/03/09/multimedia/09ukraine-family-topart/09ukraine-family-topart-thumbStandard.jpg",
                publishedDate = "2022-03-09",
                createdBy = "By Andrew E. Kramer"
            )


            assertThat(articles).contains(article)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `request to get data from api should return loading`() = runTest {

        fakeArticlesRepository.setShouldReturnLoading(true)

        fakeArticlesRepository.getAll().test {

            val articles = awaitItem()


            assert(articles is NetworkResponse.Loading)
            cancelAndIgnoreRemainingEvents()
        }

    }


    @Test
    fun `request to get data from api should fail`() = runTest {

        fakeArticlesRepository.setShouldReturnError(true)
        fakeArticlesRepository.getAll().test {
            val result = awaitItem() as NetworkResponse.Failed
            assertThat(result.message).isEqualTo("Error")
            cancelAndIgnoreRemainingEvents()
        }
    }
}