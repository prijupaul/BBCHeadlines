package uk.com.bbcheadlines.remote.mapper

interface EntityMapper<in M, out E> {
    fun mapFromRemote(type: M): E
}