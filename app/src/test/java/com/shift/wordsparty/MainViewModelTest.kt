package com.shift.wordsparty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.shift.wordsparty.Util.Constants
import com.shift.wordsparty.extentions.generateRandomNumber
import com.shift.wordsparty.ui.LedColors
import com.shift.wordsparty.ui.MainViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert
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
    fun test_generated_number_lengh_and_patteran() {
        assert(generateRandomNumber().matches(Regex(Constants.ALPHANUMERIC_REGEX)))
        Assert.assertEquals(3, generateRandomNumber().length)
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


    @Test
    fun test_guessed_answer_is_correct() {
        viewModel.currentAnswer.value = "ABB"
        viewModel.guessedAnswer.value = ""
        viewModel.updateGuessedAnswer("A")
        viewModel.updateGuessedAnswer("B")
        viewModel.updateGuessedAnswer("B")
        assertEquals("ABB", viewModel.guessedAnswer.value)
    }


    @Test
    fun test_has_player_won_if_both_answers_are_correct() {
        viewModel.currentAnswer.value = "ABB"
        viewModel.guessedAnswer.value = ""
        viewModel.updateGuessedAnswer("A")
        viewModel.updateGuessedAnswer("B")
        viewModel.updateGuessedAnswer("B")
        assertEquals(true, viewModel.checkIfPlayerHasWon())
    }

    @Test
    fun test_orange_light() {
        viewModel.currentAnswer.value = "ABB"
        assertEquals(LedColors.ORANGE.name,viewModel.checkPlayerAnswerWithPositions(0,"B"))
    }

    @Test
    fun test_green_light() {
        viewModel.currentAnswer.value = "ABB"
        assertEquals(LedColors.GREEN.name,viewModel.checkPlayerAnswerWithPositions(0,"A"))
    }

    @Test
    fun test_red_light() {
        viewModel.currentAnswer.value = "ABB"
        assertEquals(LedColors.RED.name,viewModel.checkPlayerAnswerWithPositions(0,"C"))
    }
}