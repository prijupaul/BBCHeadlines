package uk.com.bbcheadlines.remote

import io.reactivex.Flowable
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.repository.NewsRemote
import uk.com.bbcheadlines.remote.mapper.NewsEntityMapper
import javax.inject.Inject

class NewsRemoteImpl @Inject constructor(private val newsService: NewsService,
                                         private val entityMapper: NewsEntityMapper) : NewsRemote {
    override fun getNews(): Flowable<List<NewsEntity>> {
        return newsService.getNews()
                .map {
                    val entities = mutableListOf<NewsEntity>()
                    it.news.articles.forEach { entities.add(entityMapper.mapFromRemote(it)) }
                    entities
                }
    }
}