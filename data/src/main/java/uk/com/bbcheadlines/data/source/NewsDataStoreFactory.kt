package uk.com.bbcheadlines.data.source

import uk.com.bbcheadlines.data.repository.NewsCache
import uk.com.bbcheadlines.data.repository.NewsDataStore
import javax.inject.Inject

open class NewsDataStoreFactory @Inject constructor(
        private val newsCache: NewsCache,
        private val newsCacheDataStore: NewsCacheDataStore,
        private val newsRemoteDataStore: NewsRemoteDataStore ) {

    open fun retrieveDataStore(isCached: Boolean): NewsDataStore {
        return when {
            isCached && !newsCache.isExpired() -> retrieveCacheDataStore()
            else -> retrieveRemoteDataStore()
        }
    }

    open fun retrieveCacheDataStore() : NewsDataStore = newsCacheDataStore

    open fun retrieveRemoteDataStore(): NewsDataStore = newsRemoteDataStore
}