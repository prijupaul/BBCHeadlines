package uk.com.bbcheadlines.bbcheadlines.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import uk.com.bbcheadlines.bbcheadlines.MainActivity

@Subcomponent
interface MainActivitySubComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
