package uk.com.bbcheadlines.presentation.mapper

import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.presentation.model.NewsView
import javax.inject.Inject

open class NewsMapper @Inject constructor() : Mapper<NewsView, News> {
    override fun mapToView(type: News): NewsView {
        return NewsView(type.author, type.title, type.description, type.url, type.urlToImage, type.publishedAt)
    }
}