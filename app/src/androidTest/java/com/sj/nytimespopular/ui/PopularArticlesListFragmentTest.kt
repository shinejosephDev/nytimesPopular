package com.sj.nytimespopular.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.sj.nytimespopular.ArticlesAndroidTestTestData
import com.sj.nytimespopular.R
import com.sj.nytimespopular.domain.data.Article
import com.sj.nytimespopular.launchFragmentInHiltContainer
import com.sj.nytimespopular.presentation.adapter.ArticlesListAdapter
import com.sj.nytimespopular.presentation.ui.DetailFragment
import com.sj.nytimespopular.presentation.ui.ListFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PopularArticlesListFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()


    }

    @Test
    fun testIfTheCorrectDataIsDisplayedInTheRecyclerView() {

        launchFragmentInHiltContainer<ListFragment> {
            val rvArticles = this.view?.findViewById<RecyclerView>(R.id.rv_articles)
            val lm = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            rvArticles?.layoutManager = lm
            val adapter = ArticlesListAdapter(ArticlesAndroidTestTestData.articels, this)
            rvArticles?.adapter = adapter


            rvArticles?.visibility = View.VISIBLE


        }

        onView(withId(R.id.rv_articles)).check(
            matches((hasDescendant(withText("Test"))))
        )

    }


    @Test
    fun onItemClicked_navigateToDetailFragment() {


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

        val bundle = Bundle()
        bundle.putParcelable(
            "Article",
            article
        )
        launchFragmentInHiltContainer<DetailFragment>(
            fragmentArgs = bundle
        )

        onView(withText("TestTitle1")).check(matches(isDisplayed()))


    }

}
