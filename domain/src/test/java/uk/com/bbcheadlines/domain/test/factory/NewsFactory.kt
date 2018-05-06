package uk.com.bbcheadlines.domain.test.factory

import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.domain.test.factory.NewsGenerator.Companion.randomString

class NewsFactory {
    companion object {
        fun makeNewsList(count: Int) : List<News> {
            val news = mutableListOf<News>()
            repeat(count) {
                news.add(makeNews())
            }
            return news
        }

        fun makeNews(): News {
            return News(randomString(),randomString(),randomString(),randomString(),randomString(),randomString())
        }
    }
}