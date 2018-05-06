package uk.com.bbcheadlines.remote.mapper

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.com.bbcheadlines.remote.test.factory.NewsFactory
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class NewsEntityMapperTest {

    private lateinit var newsEntityMapper: NewsEntityMapper

    @Before
    fun setUp() {
        newsEntityMapper = NewsEntityMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        val newsModel = NewsFactory.makeNewsModel()
        val newsEntity = newsEntityMapper.mapFromRemote(newsModel)

        assertEquals(newsModel.author, newsEntity.author)
        assertEquals(newsModel.title, newsEntity.title)
        assertEquals(newsModel.description, newsEntity.description)
        assertEquals(newsModel.publishedAt, newsEntity.publishedAt)
    }

}