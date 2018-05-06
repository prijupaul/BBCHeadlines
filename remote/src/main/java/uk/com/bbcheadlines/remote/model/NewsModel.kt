package uk.com.bbcheadlines.remote.model

data class NewsArticles(val articles: List<NewsModel>)
data class NewsModel(val author: String, val title: String, val description: String, val url: String, val urlToImage: String, val publishedAt: String)