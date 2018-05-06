package uk.com.bbcheadlines.domain.interactor.browse

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import uk.com.bbcheadlines.domain.executor.PostExecutionThread
import uk.com.bbcheadlines.domain.executor.ThreadExecutor
import uk.com.bbcheadlines.domain.executor.interactor.browse.GetNews
import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.domain.repository.NewsRepository
import uk.com.bbcheadlines.domain.test.factory.NewsFactory

class GetNewsTest {

    private lateinit var getNews: GetNews
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockNewsRepository: NewsRepository
    private lateinit var mockPostExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        mockThreadExecutor = mock()
        mockNewsRepository = mock()
        mockPostExecutionThread = mock()
        getNews = GetNews(mockNewsRepository,mockThreadExecutor,mockPostExecutionThread)
    }

    @Test
    fun buildUsecaseObservableCallsRepository() {
        getNews.constructUseCaseObservable(null)
        verify(mockNewsRepository).getNews()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubNewsRepositoryGetNews(Flowable.just(NewsFactory.makeNewsList(2)))
        val testObserver = getNews.constructUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val news = NewsFactory.makeNewsList(2)
        stubNewsRepositoryGetNews(Flowable.just(news))
        val testObserver = getNews.constructUseCaseObservable(null).test()
        testObserver.assertValue(news)
    }

    private fun stubNewsRepositoryGetNews(single: Flowable<List<News>>?) {
        whenever(mockNewsRepository.getNews()).thenReturn(single)
    }


}