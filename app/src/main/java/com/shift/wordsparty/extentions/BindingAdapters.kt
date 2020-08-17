package com.shift.wordsparty.extentions

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.shift.wordsparty.R
import com.shift.wordsparty.ui.LedColors


//used for led colors
@BindingAdapter("android:setColors")
fun setLedColors(view: View, ledColor: String) {

    val imageView = view as ImageView
    when (ledColor) {

        LedColors.GREEN.name -> {
            view.background = ContextCompat.getDrawable(view.context, R.drawable.ic_led_green)
        }

        LedColors.RED.name -> {
            view.background = ContextCompat.getDrawable(view.context, R.drawable.ic_led_red)

        }
        LedColors.ORANGE.name -> {
            view.background = ContextCompat.getDrawable(view.context, R.drawable.ic_led_orange)

        }

        else -> {
            view.background = ContextCompat.getDrawable(view.context, R.drawable.ic_led_grey)

        }
    }
}

//used for setting current answer views
@BindingAdapter("android:currentAns", "android:textPos")
fun setCurrentAns(view: View, currentAns: String, textPos: Int) {

    val textView = view as TextView
    when (textPos) {

        0 -> {
            textView.text = currentAns[0].toString()
        }

        1 -> {
            textView.text = currentAns[1].toString()
        }
        2 -> {
            textView.text = currentAns[2].toString()
        }

        else -> {
            textView.text = "?"
        }
    }
}



