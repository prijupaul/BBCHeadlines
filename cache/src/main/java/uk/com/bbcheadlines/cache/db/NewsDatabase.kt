package uk.com.bbcheadlines.cache.db.constants

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import uk.com.bbcheadlines.cache.dao.CachedNewsDao
import uk.com.bbcheadlines.cache.model.CachedNews
import javax.inject.Inject

@Database(entities = arrayOf(CachedNews::class), version = 1)
abstract class NewsDatabase @Inject constructor() : RoomDatabase() {
    abstract fun cachedNewDao(): CachedNewsDao

    private var INSTANCE: NewsDatabase? = null
    private val lockObj = Any()

    fun getInstance(context: Context): NewsDatabase {
        if (INSTANCE == null) {
            synchronized(lockObj) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NewsDatabase::class.java, "news.db").build()
                }
            }
            return INSTANCE!!
        }
        return INSTANCE!!
    }
}