package com.shift.wordsparty.ui


import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shift.wordsparty.R
import com.shift.wordsparty.extentions.generateRandomNumber
import com.shift.wordsparty.extentions.playSound


enum class LedColors { RED, GREEN, GREY, ORANGE }

class MainViewModel : ViewModel() {

    var currentAnswer = MutableLiveData<String>()
    var guessedAnswer = MutableLiveData<String>()
    var led1 = MutableLiveData<String>()
    var led2 = MutableLiveData<String>()
    var led3 = MutableLiveData<String>()
    var isPlayerWon = MutableLiveData<Boolean>()
    var isPlayerLost = MutableLiveData<Boolean>()

    fun resetGame(isNewGame: Boolean) {
        if (isNewGame) {
            currentAnswer.value = generateRandomNumber()
        }
        isPlayerWon.value = false
        isPlayerLost.value = false
        guessedAnswer.value = ""
        led1.value = LedColors.GREY.name
        led2.value = LedColors.GREY.name
        led3.value = LedColors.GREY.name
    }

    fun tryAgainClicked(view: View) {
        resetGame(false)
        isPlayerWon.value = false
    }

    fun gameButtonClicked(view: View) {
        val button = view as Button
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


    fun checkPlayerAnswerWithPositions(pos: Int, guessedText: String): String {

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