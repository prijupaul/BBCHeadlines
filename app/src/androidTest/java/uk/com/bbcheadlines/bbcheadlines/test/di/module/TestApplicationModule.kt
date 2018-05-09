package uk.com.bbcheadlines.bbcheadlines.test.di.module

import android.app.Application
import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication
import uk.com.bbcheadlines.bbcheadlines.UiThread
import uk.com.bbcheadlines.cache.PreferencesHelper
import uk.com.bbcheadlines.data.executor.JobExecutor
import uk.com.bbcheadlines.data.repository.NewsCache
import uk.com.bbcheadlines.data.repository.NewsRemote
import uk.com.bbcheadlines.domain.executor.PostExecutionThread
import uk.com.bbcheadlines.domain.executor.ThreadExecutor
import uk.com.bbcheadlines.domain.repository.NewsRepository
import uk.com.bbcheadlines.remote.NewsService

@Module
class TestApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(): PreferencesHelper {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRepository(): NewsRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideNewsCache(): NewsCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideNewsRemote(): NewsRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
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
        return mock()
    }
}