package com.shift.wordsparty.extentions

import android.content.Context
import android.media.MediaPlayer
import com.shift.wordsparty.Util.Constants


fun generateRandomNumber(): String {
    val randomString = (1..3)
        .map { i -> kotlin.random.Random.nextInt(0, Constants.randomNumberRegex.size) }
        .map(Constants.randomNumberRegex::get)
        .joinToString("");
//    println(randomString)
    return randomString;
}

fun generateButtonSequence(): String {

    return ('A'..'C').map { it }.shuffled().joinToString("")
}

fun playSound(context: Context, file: Int) {
    val mediaPlayer: MediaPlayer? = MediaPlayer.create(context, file)
    mediaPlayer?.start()
}

