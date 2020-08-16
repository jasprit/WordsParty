package com.shift.wordsparty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shift.wordsparty.ui.LedColors
import com.shift.wordsparty.ui.MainViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel


    @Before
    fun setUp() {
        // MockitoAnnotations.initMocks(this);
        viewModel = MainViewModel()


    }


    @Test
    fun test_null() {
        assertNotNull(viewModel.guessedAnswer)
        assertNotNull(viewModel.currentAnswer)
        assertNotNull(viewModel.led1)
        assertNotNull(viewModel.led2)
        assertNotNull(viewModel.led3)
        assertNotNull(viewModel.isPlayerLost)
    }

    @Test
    fun test_new_game_always_has_gyey_led_light() {
        viewModel.resetGame(true)
        assertEquals(LedColors.GREY.name, viewModel.led1.value)
        assertEquals(LedColors.GREY.name, viewModel.led2.value)
        assertEquals(LedColors.GREY.name, viewModel.led3.value)
    }


}