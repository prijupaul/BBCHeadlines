package uk.com.bbcheadlines.bbcheadlines.ui

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.com.bbcheadlines.bbcheadlines.test.TestApplication
import uk.com.bbcheadlines.bbcheadlines.test.util.NewsFactory
import uk.com.bbcheadlines.domain.executor.model.News

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java,false,false)

    @Test
    fun activityLaunches() {
        stubNewsRepositoryGetNews(Flowable.just(NewsFactory.makeNewsList(3)))
        activity.launchActivity(null)
    }


    private fun stubNewsRepositoryGetNews(single: Flowable<List<News>>) {
        whenever(TestApplication.appComponent().newsRepository().getNews())
                .thenReturn(single)
    }
}