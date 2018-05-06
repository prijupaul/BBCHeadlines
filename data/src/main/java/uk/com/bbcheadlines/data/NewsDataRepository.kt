package uk.com.bbcheadlines.data

import io.reactivex.Completable
import io.reactivex.Flowable
import uk.com.bbcheadlines.data.mapper.NewsMapper
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.source.NewsDataStoreFactory
import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.domain.repository.NewsRepository
import javax.inject.Inject

class NewsDataRepository @Inject constructor(private val factory: NewsDataStoreFactory,
                                             private val newsMapper: NewsMapper) : NewsRepository {
    override fun clearNews(): Completable {
        return factory.retrieveCacheDataStore().clearNews()
    }

    override fun saveNews(news: List<News>): Completable {
        val newsEntities = mutableListOf<NewsEntity>()
        news.map { newsEntities.add(newsMapper.mapToEntity(it)) }
        return factory.retrieveCacheDataStore().saveNews(newsEntities)
    }

    override fun getNews(): Flowable<List<News>> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher { isCached -> factory.retrieveDataStore(isCached).getNews() }
                .flatMap { Flowable.just(it.map { newsMapper.mapFromEntity(it) }) }
                .flatMap { saveNews(it).toSingle { it }.toFlowable() }
    }
}