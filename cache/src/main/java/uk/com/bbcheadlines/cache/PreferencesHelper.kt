package uk.com.bbcheadlines.cache

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private val PREF_NEWS_PACKAGE_NAME = "org.news.android.preferences"

        private val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val newsPref: SharedPreferences

    init {
        newsPref = context.getSharedPreferences(PREF_NEWS_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = newsPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = newsPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()
}