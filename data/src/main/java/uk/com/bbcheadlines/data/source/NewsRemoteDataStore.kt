package uk.com.bbcheadlines.data.source

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.repository.NewsDataStore
import uk.com.bbcheadlines.data.repository.NewsRemote
import javax.inject.Inject

open class NewsRemoteDataStore @Inject constructor(private val newsRemote: NewsRemote) : NewsDataStore {
    override fun clearNews(): Completable =
            throw UnsupportedOperationException()


    override fun saveNews(news: List<NewsEntity>): Completable =
            throw UnsupportedOperationException()


    override fun getNews(): Flowable<List<NewsEntity>> =
            newsRemote.getNews()

    override fun isCached(): Single<Boolean> =
            throw UnsupportedOperationException()
}