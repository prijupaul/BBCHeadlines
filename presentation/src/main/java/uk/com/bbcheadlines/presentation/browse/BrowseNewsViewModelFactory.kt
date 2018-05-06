package uk.com.bbcheadlines.presentation.browse

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import uk.com.bbcheadlines.domain.executor.interactor.browse.GetNews
import uk.com.bbcheadlines.presentation.mapper.NewsMapper

open class BrowseNewsViewModelFactory(
        private val getNews: GetNews,
        private val newsMapper: NewsMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowseNewsViewModel::class.java)) {
            return BrowseNewsViewModel(getNews, newsMapper) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }
}