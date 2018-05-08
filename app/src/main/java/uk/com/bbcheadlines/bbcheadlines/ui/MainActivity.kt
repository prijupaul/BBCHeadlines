package uk.com.bbcheadlines.bbcheadlines.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import uk.com.bbcheadlines.bbcheadlines.R
import uk.com.bbcheadlines.bbcheadlines.mapper.NewsMapper
import uk.com.bbcheadlines.presentation.browse.BrowseNewsViewModel
import uk.com.bbcheadlines.presentation.browse.BrowseNewsViewModelFactory
import uk.com.bbcheadlines.presentation.data.Resource
import uk.com.bbcheadlines.presentation.data.ResourceState
import uk.com.bbcheadlines.presentation.model.NewsView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var newsAdapter: NewsAdapter
    @Inject lateinit var mapper: NewsMapper
    @Inject lateinit var viewModelFactory: BrowseNewsViewModelFactory
    @Inject lateinit var newsViewModel: BrowseNewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        newsViewModel = ViewModelProviders.of(this,viewModelFactory)
                .get(BrowseNewsViewModel::class.java)

        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        newsViewModel.getNews().observe(this,
                Observer<Resource<List<NewsView>>> {
                    it?.let { handleDataState(it.status,it.data,it.message) }
                })
    }

    private fun setupBrowseRecycler() {
        recycler_news.layoutManager = LinearLayoutManager(this)
        recycler_news.adapter = newsAdapter
    }


    private fun handleDataState(resourceState: ResourceState, data: List<NewsView>?,
                                message: String?) {
        when (resourceState) {
            ResourceState.LOADING -> Log.d("Priju","loading.. ")
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> Log.d("Priju",message)
        }
    }

    private fun setupScreenForSuccess(data: List<NewsView>?) {
        if (data != null && data.isNotEmpty()) {
            updateListView(data)
            recycler_news.visibility = View.VISIBLE
        }
    }

    private fun updateListView(data: List<NewsView>) {
        newsAdapter.news = data.map { mapper.mapToViewModel(it) }
        newsAdapter.notifyDataSetChanged()
    }

}
