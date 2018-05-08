package uk.com.bbcheadlines.remote.test.factory

import uk.com.bbcheadlines.remote.NewsService
import uk.com.bbcheadlines.remote.model.NewsModel
import uk.com.bbcheadlines.remote.test.factory.DataFactory.Companion.randomString

class NewsFactory {
    companion object Factory {
        fun makeNewsResponse(): NewsService.NewsResponse {
            val newsResponse = NewsService.NewsResponse()
            newsResponse.articles = makeNewsModelList(5)
            return newsResponse
        }

        fun makeNewsModelList(count: Int): List<NewsModel> {
            val newsEntities = mutableListOf<NewsModel>()
            repeat(count) {
                newsEntities.add(makeNewsModel())
            }
            return newsEntities
        }

        fun makeNewsModel(): NewsModel {
            return NewsModel(randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
        }
    }
}