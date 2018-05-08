package uk.com.bbcheadlines.bbcheadlines.mapper

interface Mapper<out V, in D> {
    fun mapToViewModel(type:D) : V
}