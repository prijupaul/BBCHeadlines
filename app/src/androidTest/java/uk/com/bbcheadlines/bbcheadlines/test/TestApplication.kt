package uk.com.bbcheadlines.bbcheadlines.test

import android.app.Application

class TestApplication: Application() /*, HasActivityInjector*/ {

//    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>
//
//    private lateinit var appComponent: TestApplicationComponent

    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerTestApplicationComponent.builder().application(this).build()
//        appComponent.inject(this)
    }

//    companion object {
//
//        fun appComponent(): TestApplicationComponent {
//            return (InstrumentationRegistry.getTargetContext().applicationContext as TestApplication).
//                    appComponent
//        }
//
//    }
//
//    override fun activityInjector(): AndroidInjector<Activity> {
//        return injector
//    }

}