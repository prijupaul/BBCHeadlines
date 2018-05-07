package uk.com.bbcheadlines.bbcheadlines.di.module

import dagger.Module
import dagger.Provides
import uk.com.bbcheadlines.domain.executor.interactor.browse.GetNews
import uk.com.bbcheadlines.presentation.browse.BrowseNewsViewModelFactory
import uk.com.bbcheadlines.presentation.mapper.NewsMapper

@Module
open class MainActivityModule {
    @Provides
    fun provideNewsViewModelFactory(getNews: GetNews, newWrapper: NewsMapper):
            BrowseNewsViewModelFactory {
        return BrowseNewsViewModelFactory(getNews, newWrapper)
    }
}