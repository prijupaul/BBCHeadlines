package uk.com.bbcheadlines.presentation.browse

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.subscribers.DisposableSubscriber
import uk.com.bbcheadlines.domain.executor.interactor.browse.GetNews
import uk.com.bbcheadlines.domain.executor.model.News
import uk.com.bbcheadlines.presentation.data.Resource
import uk.com.bbcheadlines.presentation.data.ResourceState
import uk.com.bbcheadlines.presentation.mapper.NewsMapper
import uk.com.bbcheadlines.presentation.model.NewsView
import javax.inject.Inject

open class BrowseNewsViewModel @Inject internal constructor(
        private val getNews: GetNews,
        private val newsMapper: NewsMapper) : ViewModel() {

    private val newsLiveData: MutableLiveData<Resource<List<NewsView>>> = MutableLiveData()

    init {
        fetchNews()
    }

    override fun onCleared() {
        getNews.dispose()
        super.onCleared()
    }

    private fun fetchNews() {
        newsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getNews.execute(NewsSubscriber())
    }

    inner class NewsSubscriber : DisposableSubscriber<List<News>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<News>?) {
            newsLiveData.postValue(Resource(ResourceState.SUCCESS,
                    t?.map { newsMapper.mapToView(it) }, null))
        }

        override fun onError(t: Throwable?) {
            newsLiveData.postValue(Resource(ResourceState.ERROR, null, t?.message))
        }
    }
}