package com.sj.nytimespopular.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (
    val id: Long,
    val title:  String,
    val abstract: String,
    val url:  String,
    val smallThumbnail: String,
    val mediumThumbnail: String,
    val largeThumbnail: String,
    val publishedDate: String,
    val createdBy: String
): Parcelable