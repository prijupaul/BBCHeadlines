package uk.com.bbcheadlines.bbcheadlines.test.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication
import uk.com.bbcheadlines.bbcheadlines.di.ApplicationComponent
import uk.com.bbcheadlines.bbcheadlines.di.module.ActivityBindingModule
import uk.com.bbcheadlines.bbcheadlines.test.TestApplication
import uk.com.bbcheadlines.bbcheadlines.test.di.module.TestApplicationModule
import uk.com.bbcheadlines.domain.executor.PostExecutionThread
import uk.com.bbcheadlines.domain.repository.NewsRepository

@Component(modules = arrayOf(TestApplicationModule::class, ActivityBindingModule::class,AndroidSupportInjectionModule::class))
@PerApplication
interface TestApplicationComponent: ApplicationComponent {
    fun newsRepository(): NewsRepository
    fun postExecutionThread(): PostExecutionThread
    fun inject(application:TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : TestApplicationComponent.Builder
        fun build(): TestApplicationComponent
    }
}