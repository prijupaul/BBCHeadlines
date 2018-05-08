package uk.com.bbcheadlines.bbcheadlines.mapper

import uk.com.bbcheadlines.bbcheadlines.model.NewsViewModel
import uk.com.bbcheadlines.presentation.model.NewsView
import javax.inject.Inject

open class NewsMapper @Inject constructor() : Mapper<NewsViewModel,NewsView> {

    override fun mapToViewModel(type: NewsView): NewsViewModel {
        return NewsViewModel(type.author,type.title,type.description,type.url,type.urlToImage,type.publishedAt)
    }
}