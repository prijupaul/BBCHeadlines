package uk.com.bbcheadlines.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import uk.com.bbcheadlines.domain.executor.model.News

interface NewsRepository {

    fun clearNews(): Completable

    fun saveNews(news: List<News>): Completable

    fun getNews(): Flowable<List<News>>

}

