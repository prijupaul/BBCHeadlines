package uk.com.bbcheadlines.bbcheadlines.test.util

import uk.com.bbcheadlines.bbcheadlines.test.util.DataFactory.Companion.randomString
import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.presentation.model.NewsView

object NewsFactory {
    fun makeNewsView(): NewsView {
        return NewsView(randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
    }

    fun makeNewsList(count: Int): List<News> {
        return mutableListOf<News>()
                .apply {
                    repeat(count) {
                        add(makeNewsModel())
                    }
                }

    }

    fun makeNewsModel(): News {
        return News(randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
    }
}