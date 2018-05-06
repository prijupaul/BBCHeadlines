package uk.com.bbcheadlines.presentation.data

open class Resource<out T> constructor(val status: ResourceState, val data: T?, val message: String?)