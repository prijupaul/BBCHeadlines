package uk.com.bbcheadlines.bbcheadlines.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import uk.com.bbcheadlines.bbcheadlines.R
import uk.com.bbcheadlines.bbcheadlines.model.NewsViewModel
import javax.inject.Inject

class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var news: List<NewsViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = news[position]
        holder.newsDescription.text = newsItem.description
        holder.newsTitle.text = newsItem.title
        Glide.with(holder.itemView.context)
                .load(newsItem.urlToImage)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.newsIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_news, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var newsIcon: ImageView
        var newsTitle: TextView
        var newsDescription: TextView

        init {
            newsIcon = view.findViewById(R.id.news_icon)
            newsTitle = view.findViewById(R.id.news_title)
            newsDescription = view.findViewById(R.id.news_description)
        }
    }
}