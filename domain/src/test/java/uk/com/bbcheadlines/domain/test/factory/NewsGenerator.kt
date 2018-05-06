package uk.com.bbcheadlines.domain.test.factory

import net.bytebuddy.utility.RandomString

class NewsGenerator {
    companion object {
        fun randomString(): String {
            return RandomString.make(23)
        }
    }
}