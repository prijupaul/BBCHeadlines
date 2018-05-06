package uk.com.bbcheadlines.data.repository

import io.reactivex.Flowable
import uk.com.bbcheadlines.data.model.NewsEntity

interface NewsRemote {
    fun getNews(): Flowable<List<NewsEntity>>
}