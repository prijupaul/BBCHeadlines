package uk.com.bbcheadlines.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import uk.com.bbcheadlines.data.model.NewsEntity

interface NewsDataStore {
    fun clearNews(): Completable
    fun saveNews(news: List<NewsEntity>): Completable
    fun getNews(): Flowable<List<NewsEntity>>
    fun isCached(): Single<Boolean>
}