package com.sj.nytimespopular.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sj.nytimespopular.R
import com.sj.nytimespopular.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Handle the back button event
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(viewLifecycleOwner, onBackPressedCallback)

        val title = binding.articleDetailsContent.titleDes
        val description = binding.articleDetailsContent.descriptionDes
        val createdByTitleDes = binding.articleDetailsContent.createdByDes
        val sourceDes = binding.articleDetailsContent.sourceDes
        val headerImage = binding.collapsingToolbar.image
        val btnReadMore = binding.articleDetailsContent.btnReadMore

        title.text = article.title
        description.text = article.abstract
        createdByTitleDes.text = article.createdBy
        sourceDes.text = article.publishedDate


        btnReadMore.setOnClickListener {
            val readMoreUri = Uri.parse(article.url)
            openCustomTabForReadMore(readMoreUri)
        }

        Picasso.get().load(article.mediumThumbnail).into(headerImage)
    }

    private fun openCustomTabForReadMore(uri: Uri) {
        val customTabIntent: CustomTabsIntent = CustomTabsIntent.Builder()
            .setToolbarColor(requireActivity().getColor(R.color.purple_700))
            .build()

        val packageName = "com.android.chrome"

        customTabIntent.intent.setPackage(packageName)
        customTabIntent.launchUrl(requireActivity(), uri)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}