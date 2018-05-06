package uk.com.bbcheadlines.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.com.bbcheadlines.data.repository.NewsCache

@RunWith(JUnit4::class)
class NewsDataStoreFactoryTest {

    private lateinit var newsDataStoreFactory: NewsDataStoreFactory
    private lateinit var newsCache: NewsCache
    private lateinit var newsCacheDataStore: NewsCacheDataStore
    private lateinit var newsRemoteDataStore: NewsRemoteDataStore

    @Before
    fun setUp() {
        newsCache = mock()
        newsCacheDataStore = mock()
        newsRemoteDataStore = mock()
        newsDataStoreFactory = NewsDataStoreFactory(newsCache, newsCacheDataStore, newsRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenNoCacheReturnsRemoteDataStore() {
        stubNewsCacheIsCached(Single.just(false))
        val newsDataStore = newsDataStoreFactory.retrieveDataStore(false)
        assert(newsDataStore is NewsRemoteDataStore)
    }

    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        val newsDataStore = newsDataStoreFactory.retrieveRemoteDataStore()
        assert(newsDataStore is NewsRemoteDataStore)
    }

    // <editor-fold desc = "Retrieve cache data store">
    private fun stubNewsCacheIsCached(single: Single<Boolean>) {
        whenever(newsCache.isCached())
                .thenReturn(single)
    }

    private fun stubNewsCacheIsExpired(isExpired: Boolean) {
        whenever(newsCache.isExpired())
                .thenReturn(isExpired)

    }
}