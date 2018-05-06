package uk.com.bbcheadlines.remote.test.factory

import net.bytebuddy.utility.RandomString

class DataFactory {
    companion object {
        fun randomString(): String {
            return RandomString.make(23)
        }
    }
}