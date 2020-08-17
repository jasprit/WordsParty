package com.shift.wordsparty

import com.shift.wordsparty.Util.Constants
import com.shift.wordsparty.extentions.generateRandomNumber
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }

    // check MainViewModelTest class for more cases..

    @Test
    fun test_generated_number_lengh_and_patteran() {
        assert(generateRandomNumber().matches(Regex(Constants.ALPHANUMERIC_REGEX)))
        assertEquals(3, generateRandomNumber().length)
    }

}