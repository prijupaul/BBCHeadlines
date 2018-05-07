package uk.com.bbcheadlines.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import uk.com.bbcheadlines.cache.db.constants.NewsConstants
import uk.com.bbcheadlines.cache.model.CachedNews

@Dao
abstract class CachedNewsDao {
    @Query(NewsConstants.QUERY_NEWS)
    abstract fun getNews(): List<CachedNews>

    @Query(NewsConstants.DELETE_ALL_NEWS)
    abstract fun clearNews()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNews(cachedNews: CachedNews)
}