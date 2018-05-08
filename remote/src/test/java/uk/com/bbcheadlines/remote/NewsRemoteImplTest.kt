package uk.com.bbcheadlines.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.remote.mapper.NewsEntityMapper
import uk.com.bbcheadlines.remote.test.factory.NewsFactory

@RunWith(JUnit4::class)
class NewsRemoteImplTest {

    private lateinit var entityMapper: NewsEntityMapper
    private lateinit var newsService: NewsService
    private lateinit var newsRemoteImpl: NewsRemoteImpl

    @Before
    fun setUp() {
        entityMapper = mock()
        newsService = mock()
        newsRemoteImpl = NewsRemoteImpl(newsService,entityMapper)
    }

    @Test
    fun getNewsCompletes() {
        stubNewsServiceGetNews(Flowable.just(NewsFactory.makeNewsResponse()))
        val testObserver = newsRemoteImpl.getNews().test()
        testObserver.assertComplete()
    }

    @Test
    fun getNewsReturnsData() {
        val newsResponse = NewsFactory.makeNewsResponse()
        stubNewsServiceGetNews(Flowable.just(newsResponse))
        val newsEntities = mutableListOf<NewsEntity>()
        newsResponse.newsArticles.articles.forEach {
            newsEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = newsRemoteImpl.getNews().test()
        testObserver.assertValue(newsEntities)
    }

    private fun stubNewsServiceGetNews(observable: Flowable<NewsService.NewsResponse>) {
        whenever(newsService.getNews())
                .thenReturn(observable)
    }

}