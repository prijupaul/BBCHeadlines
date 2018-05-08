package uk.com.bbcheadlines.remote

import io.reactivex.Flowable
import retrofit2.http.GET
import uk.com.bbcheadlines.remote.model.NewsModel

interface NewsService {
    @GET("top-headlines?sources=bbc-news&apiKey=7587c17255b2436681ec305af369f671")
    fun getNews() : Flowable<NewsResponse>

    class NewsResponse {
        lateinit var articles: List<NewsModel>
    }
}