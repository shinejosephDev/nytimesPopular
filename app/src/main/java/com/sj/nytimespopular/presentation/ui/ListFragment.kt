package com.sj.nytimespopular.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sj.nytimespopular.databinding.FragmentListBinding
import com.sj.nytimespopular.domain.data.Article
import com.sj.nytimespopular.presentation.adapter.ArticleViewOnClickListener
import com.sj.nytimespopular.presentation.adapter.ArticlesListAdapter
import com.sj.nytimespopular.presentation.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), ArticleViewOnClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutPopupError.btnRetry.setOnClickListener {
            viewmodel.getAllArticles()
        }

        viewmodel.articles.observe(viewLifecycleOwner) {
            it?.let { it1 -> setUpRecyclerView(it1, binding.rvArticles) }
        }

        viewmodel.loadingState.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                binding.layoutPopupError.root.visibility = View.GONE
                binding.rvArticles.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.layoutPopupError.root.visibility = View.GONE

            }
        }

        viewmodel.errorMessage.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.layoutPopupError.root.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.layoutPopupError.tvErrorMessage.text = it
            } else {
                binding.layoutPopupError.root.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    lateinit var articles: ArrayList<Article>

    private fun setUpRecyclerView(
        it: ArrayList<Article>, rvArticles: RecyclerView
    ) {
        articles = ArrayList()
        articles.addAll(it)
        val adapter = ArticlesListAdapter(it, this@ListFragment)
        rvArticles.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )

        rvArticles.adapter = adapter

        rvArticles.visibility = View.VISIBLE
    }

    override fun onItemClick(position: Int) {
        val article = articles[position]
        val bundle: Bundle?
        bundle = Bundle()
        bundle.putParcelable("article", article)

        val action = ListFragmentDirections.actionNewsListFragmentToArticleDetailsFragment(article)
        findNavController().navigate(action)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}