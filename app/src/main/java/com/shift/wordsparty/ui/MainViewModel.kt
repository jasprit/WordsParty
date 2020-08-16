package com.shift.wordsparty.ui

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shift.wordsparty.R
import com.shift.wordsparty.extentions.generateRandomNumber
import com.shift.wordsparty.extentions.playSound


enum class LedColors { RED, GREEN, GREY, ORANGE }

class MainViewModel : ViewModel() {

    val mainViewModelLog = "MainViewModel"

    var currentAnswer = MutableLiveData<String>()
    var guessedAnswer = MutableLiveData<String>()
    var led1 = MutableLiveData<String>()
    var led2 = MutableLiveData<String>()
    var led3 = MutableLiveData<String>()
    var isPlayerLost = MutableLiveData<Boolean>()

    var jassi =true


    fun resetGame(isNewGame: Boolean) {
        if (isNewGame) {
            currentAnswer.value = generateRandomNumber()
        }

        guessedAnswer.value = ""
        led1.value = LedColors.GREY.name
        led2.value = LedColors.GREY.name
        led3.value = LedColors.GREY.name
        guessedAnswer.observeForever {
            Log.d(mainViewModelLog, "guessedAns $it")
        }
    }

    fun tryAgainClicked(view: View) {
        resetGame(false)
        isPlayerLost.value = false
    }

    fun gameButtonClicked(view: View) {
        var button = view as Button
        Log.d(mainViewModelLog, "buttonClicked ${button.text}")

        if (guessedAnswer.value?.length ?: 0 < 3) playSound(view.context, R.raw.button_click)
        updateGuessedAnswer(button.text.toString())
    }

    fun updateGuessedAnswer(buttonName: String) {

        when (guessedAnswer.value?.length ?: 0) {

            0 -> {
                led1.value = checkPlayerAnswerWithPositions(0, buttonName)
            }

            1 -> {
                led2.value = checkPlayerAnswerWithPositions(1, buttonName)
            }

            2 -> {
                led3.value = checkPlayerAnswerWithPositions(2, buttonName)
                //   resetGame()

            }
        }

        guessedAnswer.value = guessedAnswer.value + buttonName
    }


    private fun checkPlayerAnswerWithPositions(pos: Int, guessedText: String): String {

        // Green indicates that the button pressed was correct for this position.
        if (currentAnswer.value?.get(pos).toString() == guessedText)
            return LedColors.GREEN.name

        // Orange indicates that the button pressed was wrong for this position
        if (currentAnswer.value?.contains(guessedText) == true)
            return LedColors.ORANGE.name

        //Red indicates that the button pressed was wrong for this position, and does not appear in a different position
        return LedColors.RED.name

    }


    fun checkIfPlayerHasWon(): Boolean {
        if (led1.value == LedColors.GREEN.name && led2.value == LedColors.GREEN.name && led3.value == LedColors.GREEN.name)
            return true

        return false
    }


}