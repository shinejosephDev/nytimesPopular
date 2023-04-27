package com.sj.nytimespopular.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sj.nytimespopular.R
import com.sj.nytimespopular.databinding.ListItemBinding
import com.sj.nytimespopular.domain.data.Article
import com.sj.nytimespopular.utils.CircleTransform
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import timber.log.Timber

class ArticlesListAdapter(
    private val articles: ArrayList<Article>,
    private val articleViewOnClickListener: ArticleViewOnClickListener
) :
    RecyclerView.Adapter<ArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding!!.tvArticleTitle.text = article.title
        holder.binding.createdBy.text = article.createdBy
        holder.binding.dateRow.date.text = article.publishedDate

        holder.binding.root.setOnClickListener {
            articleViewOnClickListener.onItemClick(position)
        }
        if (article.smallThumbnail.isNotEmpty())
            Picasso.get()
                .load(article.smallThumbnail)
                .transform(CircleTransform())
                .placeholder(R.drawable.ic_launcher_foreground)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.binding.image, object : Callback {
                    override fun onSuccess() {
                        Timber.tag("Picasso").d("onSuccess: success ")
                    }

                    override fun onError(e: Exception?) {
                        Timber.tag("Picasso").d("onError: ${e?.message}")
                        Timber.tag("Picasso").d("onError: ${e?.printStackTrace()}")
                    }

                })
    }
}

class ArticleViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val binding = DataBindingUtil.bind<ListItemBinding>(binding.root)

}

interface ArticleViewOnClickListener {
    fun onItemClick(position: Int)
}