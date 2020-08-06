package com.pustovit.tmdbclient

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(BuildConfig.POSTER_BASE_URL+BuildConfig.POSTER_LARGE_SIZE_PATH)
        assertEquals(4, 2 + 2)
    }
}