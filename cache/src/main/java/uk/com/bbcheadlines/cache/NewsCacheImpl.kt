package uk.com.bbcheadlines.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import uk.com.bbcheadlines.cache.db.constants.NewsDatabase
import uk.com.bbcheadlines.cache.mapper.NewsEntityMapper
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.repository.NewsCache
import javax.inject.Inject

class NewsCacheImpl @Inject constructor(val newsDatabase: NewsDatabase,
                                        private val entityMapper: NewsEntityMapper,
                                        private val preferencesHelper: PreferencesHelper) : NewsCache {

    private val EXPIRATION_TIME = (60 * 1 * 1000).toLong()

    internal fun getDatabase(): NewsDatabase {
        return newsDatabase
    }

    override fun clearNews(): Completable {
        return Completable.defer {
            newsDatabase.cachedNewDao().clearNews()
            Completable.complete()
        }
    }

    override fun saveNews(news: List<NewsEntity>): Completable {
        return Completable.defer {
            news.forEach {
                newsDatabase.cachedNewDao().insertNews(
                        entityMapper.mapToCached(it)
                )
            }
            Completable.complete()
        }
    }

    override fun getNews(): Flowable<List<NewsEntity>> {
        return Flowable.defer {
            Flowable.just(newsDatabase.cachedNewDao().getNews())
        }.map {
            it.map { entityMapper.mapFromCached(it) }
        }
    }

    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(newsDatabase.cachedNewDao().getNews().isNotEmpty())
        }
    }

    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }
}