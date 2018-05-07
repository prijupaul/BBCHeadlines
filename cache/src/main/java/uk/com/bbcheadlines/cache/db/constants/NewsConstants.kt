package uk.com.bbcheadlines.cache.db.constants

object NewsConstants {
    const val TABLE_NAME = "News"
    const val QUERY_NEWS = "SELECT * FROM $TABLE_NAME"
    const val DELETE_ALL_NEWS = "DELETE FROM $TABLE_NAME"
}