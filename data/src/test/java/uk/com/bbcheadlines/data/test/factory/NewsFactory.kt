package uk.com.bbcheadlines.data.test.factory

import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.test.factory.NewsGenerator.Companion.randomString
import uk.com.bbcheadlines.domain.executor.model.News

class NewsFactory {
    companion object Factory {

        fun makeNewsEntity(): NewsEntity {
            return NewsEntity(randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
        }

        fun makeNews(): News {
            return News(randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
        }

        fun makeNewsEntityList(count: Int): List<NewsEntity> {
            val newsEntities = mutableListOf<NewsEntity>()
            repeat(count) {
                newsEntities.add(makeNewsEntity())
            }
            return newsEntities
        }

        fun makeNewsList(count: Int): List<News> {
            val news = mutableListOf<News>()
            repeat(count) {
                news.add(makeNews())
            }
            return news
        }
    }
}