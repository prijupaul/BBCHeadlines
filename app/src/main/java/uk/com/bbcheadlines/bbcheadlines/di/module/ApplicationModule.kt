package uk.com.bbcheadlines.bbcheadlines.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication
import uk.com.bbcheadlines.bbcheadlines.BuildConfig
import uk.com.bbcheadlines.bbcheadlines.UiThread
import uk.com.bbcheadlines.cache.NewsCacheImpl
import uk.com.bbcheadlines.cache.PreferencesHelper
import uk.com.bbcheadlines.cache.db.constants.NewsDatabase
import uk.com.bbcheadlines.cache.mapper.NewsEntityMapper
import uk.com.bbcheadlines.data.NewsDataRepository
import uk.com.bbcheadlines.data.executor.JobExecutor
import uk.com.bbcheadlines.data.mapper.NewsMapper
import uk.com.bbcheadlines.data.repository.NewsCache
import uk.com.bbcheadlines.data.repository.NewsRemote
import uk.com.bbcheadlines.data.source.NewsDataStoreFactory
import uk.com.bbcheadlines.domain.executor.PostExecutionThread
import uk.com.bbcheadlines.domain.executor.ThreadExecutor
import uk.com.bbcheadlines.domain.repository.NewsRepository
import uk.com.bbcheadlines.remote.NewsRemoteImpl
import uk.com.bbcheadlines.remote.NewsService
import uk.com.bbcheadlines.remote.NewsServiceFactory

@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferenceHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @PerApplication
    internal fun provideNewsRepository(factory: NewsDataStoreFactory,
                                       mapper: NewsMapper): NewsRepository {
        return NewsDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideNewsCache(database: NewsDatabase,
                                  entityMapper: NewsEntityMapper,
                                  helper: PreferencesHelper): NewsCache {
        return NewsCacheImpl(database, entityMapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideNewsRemote(service: NewsService,
                                   factory: uk.com.bbcheadlines.remote.mapper.NewsEntityMapper)
            : NewsRemote {
        return NewsRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor) : ThreadExecutor {
        return jobExecutor
    }


    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideNewsService(): NewsService {
        return NewsServiceFactory.makeNewsHttpService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelProvider.NewInstanceFactory()
    }

    @Provides
    @PerApplication
    internal fun provideNewssDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(application.applicationContext,
                NewsDatabase::class.java, "News.db")
                .build()
    }

}