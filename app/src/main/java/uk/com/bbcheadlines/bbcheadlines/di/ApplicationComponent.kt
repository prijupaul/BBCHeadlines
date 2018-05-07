package uk.com.bbcheadlines.bbcheadlines.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import uk.com.bbcheadlines.bbcheadlines.NewsApplication
import uk.com.bbcheadlines.bbcheadlines.di.module.ActivityBindingModule
import uk.com.bbcheadlines.bbcheadlines.di.module.ApplicationModule

@Component(modules = arrayOf(ActivityBindingModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class))
interface ApplicationComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: NewsApplication)
}