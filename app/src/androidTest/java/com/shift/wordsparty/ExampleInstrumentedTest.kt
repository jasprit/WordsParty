package com.shift.wordsparty


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.shift.wordsparty.ui.MainActivity
import com.shift.wordsparty.ui.MainViewModel
import junit.framework.Assert.assertEquals
import org.hamcrest.core.IsNot.not

import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.shift.wordsparty", appContext.packageName)
    }

    @Test
    fun test_all_three_buttons_are_clickable() {
        onView(withId(R.id.buttonA)).perform(click())
        onView(withId(R.id.buttonB)).perform(click())
        onView(withId(R.id.buttonC)).perform(click())
    }

    @Test
    fun test_guessed_answer_with_buttons() {
        onView(withId(R.id.buttonA)).perform(click())
        onView(withId(R.id.buttonB)).perform(click())
        onView(withId(R.id.buttonC)).perform(click())
        onView(withId(R.id.tvGuessed)).check(matches(withText("ABC")))
    }

    @Test
    fun test_try_again_button_is_hidden_on_app_start() {
        onView(withText("Try Again")).check(matches(not(isDisplayed())))
    }


//    // stop device animations in developer options before test this.
//    @Test
//    fun test_try_again_button_should_visible_if_guessed_answer_is_wrong() {
//
//        viewModel.setNewCurrentAns("AAA")
//
//        onView(withId(R.id.buttonA)).perform(click())
//        onView(withId(R.id.buttonB)).perform(click())
//        onView(withId(R.id.buttonB)).perform(click())
//        onView(withText("Try Again")).check(matches((isDisplayed())))
//
//    }

//    @Test
//    fun test_victory_dialog_is_displayed_if_player_has_won() {
//
//        viewModel.setNewCurrentAns("AAA")
//
//        onView(withId(R.id.buttonA)).perform(click())
//        onView(withId(R.id.buttonA)).perform(click())
//        onView(withId(R.id.buttonA)).perform(click())
//        onView(withText("Hurray"))
//            .inRoot(isDialog())
//            .check(matches(isDisplayed()))
//    }


}