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


@BindingAdapter("android:setColors")
fun setLedColors(view: View, ledColor: String) {

    val imageView = view as ImageView
    when (ledColor) {

        LedColors.GREEN.name -> {
            view.setColorFilter(
                ContextCompat.getColor(view.context, R.color.colorGreen),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }

        LedColors.RED.name -> {
            view.setColorFilter(
                ContextCompat.getColor(view.context, R.color.colorRed),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }
        LedColors.ORANGE.name -> {
            view.setColorFilter(
                ContextCompat.getColor(view.context, R.color.colorOrange),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }

        else -> {
            view.setColorFilter(
                ContextCompat.getColor(view.context, R.color.colorGreyDark),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
        }

    }


}