package com.sj.nytimespopular.data.models.mapper

import com.sj.nytimespopular.data.models.Article

fun Article.toDomainArticle() = com.sj.nytimespopular.domain.data.Article(
    id = this.id,
    title = this.title,
    abstract = this.abstract,
    url = this.url,
    smallThumbnail = this.media.firstOrNull()?.mediaMetadata?.get(0)?.url ?: "",
    mediumThumbnail = this.media.firstOrNull()?.mediaMetadata?.get(1)?.url ?: "",
    largeThumbnail = this.media.firstOrNull()?.mediaMetadata?.get(2)?.url ?: "",
    publishedDate = this.publishedDate,
    createdBy = this.byline
)