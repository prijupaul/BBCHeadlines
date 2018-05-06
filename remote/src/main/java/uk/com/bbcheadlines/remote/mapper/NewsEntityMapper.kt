package uk.com.bbcheadlines.remote.mapper

import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.remote.model.NewsModel
import javax.inject.Inject

open class NewsEntityMapper @Inject constructor() : EntityMapper<NewsModel, NewsEntity> {

    override fun mapFromRemote(type: NewsModel): NewsEntity {
        return NewsEntity(type.author,type.title,type.description,type.url,type.urlToImage,type.publishedAt)
    }
}