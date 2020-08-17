package com.shift.wordsparty.extentions

import android.content.Context
import android.media.MediaPlayer
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.shift.wordsparty.R
import com.shift.wordsparty.Util.Constants
import com.shift.wordsparty.ui.LedColors



fun generateRandomNumber(): String {

    val randomString = (1..3)
        .map { i -> kotlin.random.Random.nextInt(0, Constants.randomNumberRegex.size) }
        .map(Constants.randomNumberRegex::get)
        .joinToString("");
//    println(randomString)
    return randomString;
}

fun playSound(context: Context,file:Int){
    var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, file)
    mediaPlayer?.start()
}

