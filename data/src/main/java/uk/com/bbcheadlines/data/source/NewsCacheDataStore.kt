package uk.com.bbcheadlines.data.source

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.repository.NewsCache
import uk.com.bbcheadlines.data.repository.NewsDataStore
import javax.inject.Inject

open class NewsCacheDataStore @Inject constructor(private val newsCache: NewsCache) : NewsDataStore {

    override fun clearNews(): Completable =
            newsCache.clearNews()

    override fun saveNews(news: List<NewsEntity>): Completable =
            newsCache.saveNews(news)

    override fun getNews(): Flowable<List<NewsEntity>> =
            newsCache.getNews()

    override fun isCached(): Single<Boolean> =
            newsCache.isCached()
}