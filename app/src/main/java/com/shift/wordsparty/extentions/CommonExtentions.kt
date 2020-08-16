package com.shift.wordsparty.extentions

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.shift.wordsparty.R
import com.shift.wordsparty.ui.LedColors

fun generateRandomNumber(): String {
    val charPool: List<Char> = ('A'..'C') + ('A'..'C') + ('A'..'C')

    val randomString = (1..3)
        .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("");
//    println(randomString)
    return randomString;
}

