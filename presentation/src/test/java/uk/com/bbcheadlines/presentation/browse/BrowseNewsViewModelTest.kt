package uk.com.bbcheadlines.presentation.browse

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*

import io.reactivex.subscribers.DisposableSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mock
import uk.com.bbcheadlines.domain.executor.interactor.browse.GetNews
import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.presentation.browse.test.factory.NewsFactory
import uk.com.bbcheadlines.presentation.data.ResourceState
import uk.com.bbcheadlines.presentation.mapper.NewsMapper
import uk.com.bbcheadlines.presentation.model.NewsView

@RunWith(JUnit4::class)
class BrowseNewsViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock lateinit var getNews: GetNews
    @Mock lateinit var newsMapper: NewsMapper

    @Captor
    private lateinit var captor: KArgumentCaptor<DisposableSubscriber<List<News>>>

    private lateinit var newsViewModel: BrowseNewsViewModel

    @Before
    fun setUp() {
        captor = argumentCaptor<DisposableSubscriber<List<News>>>()
        getNews = mock()
        newsMapper = mock()
        newsViewModel = BrowseNewsViewModel(getNews,newsMapper)
    }

    @Test
    fun getNewsExecutesUseCase() {
        newsViewModel.getNews()
        verify(getNews, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun getNewsReturnsSuccess() {
        val list = NewsFactory.makeNewsList(2)
        val viewList = NewsFactory.makeNewsViewList(2)
        stubNewsMapperMapToView(viewList[0], list[0])
        stubNewsMapperMapToView(viewList[1],list[1])

        newsViewModel.getNews()

        verify(getNews).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(newsViewModel.getNews().value?.status == ResourceState.SUCCESS)
    }

    @Test
    fun getNewsReturnsDataOnSuccess() {
        val list = NewsFactory.makeNewsList(2)
        val viewList = NewsFactory.makeNewsViewList(2)
        stubNewsMapperMapToView(viewList[0], list[0])
        stubNewsMapperMapToView(viewList[1],list[1])

        newsViewModel.getNews()

        verify(getNews).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(newsViewModel.getNews()?.value?.data == viewList)
    }

    // <editor-fold desc="Error>
    @Test
    fun getNewsReturnsError() {
        newsViewModel.getNews()

        verify(getNews).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(newsViewModel.getNews().value?.status == ResourceState.ERROR)
    }

    @Test
    fun getTestFAilsAndContainsNoData() {
        newsViewModel.getNews()

        verify(getNews).execute(captor.capture(),eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(newsViewModel.getNews().value?.data == null)
    }

    //</editor-fold>

    @Test
    fun getNewsReturnsLoading() {
        newsViewModel.getNews()
        assert(newsViewModel.getNews().value?.status == ResourceState.LOADING)
    }

    @Test
    fun getNewsContainsNoDataWhileLoading() {
        newsViewModel.getNews()
        assert(newsViewModel.getNews().value?.data == null)
    }



    private fun stubNewsMapperMapToView(newsView: NewsView, news: News) {
        whenever(newsMapper.mapToView(news))
                .thenReturn(newsView)
    }
}