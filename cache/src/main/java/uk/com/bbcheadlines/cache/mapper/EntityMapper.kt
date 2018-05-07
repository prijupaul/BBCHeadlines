package uk.com.bbcheadlines.cache.mapper

interface EntityMapper<T, V> {
    fun mapFromCached(type: T): V
    fun mapToCached(type: V): T
}