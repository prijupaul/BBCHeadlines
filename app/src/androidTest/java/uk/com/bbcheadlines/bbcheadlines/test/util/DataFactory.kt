package uk.com.bbcheadlines.bbcheadlines.test.util

import net.bytebuddy.utility.RandomString

class DataFactory {
    companion object {
        fun randomString(): String {
            return RandomString.make(23)
        }
    }
}