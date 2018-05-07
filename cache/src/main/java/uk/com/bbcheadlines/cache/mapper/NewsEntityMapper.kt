package uk.com.bbcheadlines.cache.mapper

import uk.com.bbcheadlines.cache.model.CachedNews
import uk.com.bbcheadlines.data.model.NewsEntity
import javax.inject.Inject

open class NewsEntityMapper @Inject constructor() : EntityMapper<CachedNews, NewsEntity> {

    override fun mapFromCached(type: CachedNews): NewsEntity {
        return NewsEntity(type.author, type.title, type.description, type.url, type.urlToImage, type.publishedAt)
    }

    override fun mapToCached(type: NewsEntity): CachedNews {
        return CachedNews(type.author, type.title, type.description, type.url, type.urlToImage, type.publishedAt)
    }
}