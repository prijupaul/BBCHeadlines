package uk.com.bbcheadlines.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import uk.com.bbcheadlines.cache.db.constants.NewsConstants

@Entity(tableName = NewsConstants.TABLE_NAME)
data class CachedNews(
        val author: String,
        val title: String,
        val description: String,
        @PrimaryKey
        val url: String,
        val urlToImage: String,
        val publishedAt: String
)