package uk.com.bbcheadlines.data.mapper

import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.domain.executor.model.News
import javax.inject.Inject

open class NewsMapper @Inject constructor() : Mapper<NewsEntity, News> {

    override fun mapFromEntity(type: NewsEntity): News {
        return News(type.author, type.title, type.description, type.url, type.urlToImage, type.publishedAt)
    }

    override fun mapToEntity(type: News): NewsEntity {
        return NewsEntity(type.author, type.title, type.description, type.url, type.urlToImage, type.publishedAt)
    }
}