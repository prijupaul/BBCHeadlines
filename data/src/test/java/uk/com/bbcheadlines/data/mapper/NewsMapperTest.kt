package uk.com.bbcheadlines.data.mapper

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.test.factory.NewsFactory
import uk.com.bbcheadlines.domain.executor.model.News
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class NewsMapperTest {
    private lateinit var newsMapper: NewsMapper

    @Before
    fun setUp() {
        newsMapper = NewsMapper()
    }

    @Test
    fun mapFromEntityMapsData() {
        val newsEntity = NewsFactory.makeNewsEntity()
        val news = newsMapper.mapFromEntity(newsEntity)
        assertNewsDataEquality(newsEntity, news)
    }

    @Test
    fun mapToEntityMapsData() {
        val cachedNews = NewsFactory.makeNews()
        val bufferEntity = newsMapper.mapToEntity(cachedNews)
        assertNewsDataEquality(bufferEntity, cachedNews)
    }

    private fun assertNewsDataEquality(newsEntity: NewsEntity, news: News) {
        assertEquals(newsEntity.author, news.author)
        assertEquals(newsEntity.description, news.description)
        assertEquals(newsEntity.title, news.title)
        assertEquals(newsEntity.publishedAt, news.publishedAt)
        assertEquals(newsEntity.url, news.url)
        assertEquals(newsEntity.urlToImage, news.urlToImage)
    }
}