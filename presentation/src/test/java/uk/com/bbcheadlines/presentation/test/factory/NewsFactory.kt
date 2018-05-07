package uk.com.bbcheadlines.presentation.browse.test.factory

import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.presentation.browse.test.factory.DataFactory.Companion.randomString
import uk.com.bbcheadlines.presentation.model.NewsView

class NewsFactory {
    companion object {

        fun makeNewsList(count: Int): List<News> {
            return mutableListOf<News>().apply {
                repeat(count) {
                    add(makeNewsModel())
                }
            }
        }

        fun makeNewsModel(): News {
            return News(randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
        }

        fun makeNewsViewList(count: Int): List<NewsView> {
            return mutableListOf<NewsView>().apply {
                repeat(count) {
                    add(makeNewView())
                }
            }
        }

        private fun makeNewView(): NewsView {
            return NewsView(randomString(), randomString(), randomString(), randomString(), randomString(), randomString())
        }
    }
}