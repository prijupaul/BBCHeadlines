package uk.com.bbcheadlines.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.repository.NewsRemote
import uk.com.bbcheadlines.data.test.factory.NewsFactory

@RunWith(JUnit4::class)
class NewsRemoteDataStoreTest {
    private lateinit var newsRemoteDataStore: NewsRemoteDataStore
    private lateinit var newsRemote: NewsRemote

    @Before
    fun setUp() {
        newsRemote = mock()
        newsRemoteDataStore = NewsRemoteDataStore(newsRemote)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearNewsThrowsException() {
        newsRemoteDataStore.clearNews().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveNewsThrowsException() {
        newsRemoteDataStore.saveNews(NewsFactory.makeNewsEntityList(2)).test()
    }

    @Test
    fun getNewsCompletes() {
        stubNewsCacheGetNews(Flowable.just(NewsFactory.makeNewsEntityList(2)))
        val testObserver = newsRemote.getNews().test()
        testObserver.assertComplete()
    }

    private fun stubNewsCacheGetNews(just: Flowable<List<NewsEntity>>?) {
        whenever(newsRemote.getNews())
                .thenReturn(just)
    }

}