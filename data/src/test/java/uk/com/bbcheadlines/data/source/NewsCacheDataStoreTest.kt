package uk.com.bbcheadlines.data.source

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.com.bbcheadlines.data.model.NewsEntity
import uk.com.bbcheadlines.data.repository.NewsCache
import uk.com.bbcheadlines.data.test.factory.NewsFactory

@RunWith(JUnit4::class)
class NewsCacheDataStoreTest {

    private lateinit var newsCacheDataStore: NewsCacheDataStore
    private lateinit var newsCache: NewsCache

    @Before
    fun setUp() {
        newsCache = mock()
        newsCacheDataStore = NewsCacheDataStore(newsCache)
    }

    @Test
    fun clearNewsComplete() {
        stubNewsCacheClearNews(Completable.complete())
        val testObserver = newsCacheDataStore.clearNews().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveNewsCompletes() {
        stubNewsCacheSaveNews(Completable.complete())
        val testObserver = newsCacheDataStore.saveNews(
                NewsFactory.makeNewsEntityList(2)).test()
        testObserver.assertComplete()
    }

    @Test
    fun getNewsCompletes() {
        stubNewsCacheGetNews(Flowable.just(
                NewsFactory.makeNewsEntityList(2)))
        val testObserver = newsCacheDataStore.getNews().test()
        testObserver.assertComplete()
    }

    //<editor-fold desc="Stub helper methods">
    private fun stubNewsCacheSaveNews(completable: Completable) {
        whenever(newsCache.saveNews(any()))
                .thenReturn(completable)
    }

    private fun stubNewsCacheGetNews(single: Flowable<List<NewsEntity>>) {
        whenever(newsCache.getNews())
                .thenReturn(single)
    }

    private fun stubNewsCacheClearNews(completable: Completable) {
        whenever(newsCache.clearNews())
                .thenReturn(completable)
    }
    //</editor-fold>
}