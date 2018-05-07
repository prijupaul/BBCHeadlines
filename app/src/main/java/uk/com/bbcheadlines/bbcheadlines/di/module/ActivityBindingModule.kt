package uk.com.bbcheadlines.bbcheadlines.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity
import uk.com.bbcheadlines.bbcheadlines.MainActivity

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}