package com.shift.wordsparty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shift.wordsparty.R
import com.shift.wordsparty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.lifecycleOwner = this
        binding?.viewmodel = viewModel


        viewModel.resetGame(true)
        setObservables()
    }


    private fun setObservables() {
        viewModel.guessedAnswer.observe(this, Observer {
            if (it.length == 3) {
                showDialog(viewModel.checkIfPlayerHasWon())
            }
        })
    }

    private fun showDialog(isPlayerWon: Boolean) {
        MaterialAlertDialogBuilder(this)
            .setCancelable(false)
            .setTitle(if (isPlayerWon) "Hurray" else "Oops!!!!")
            .setMessage(if (isPlayerWon) "Can't wait to play more." else "I will try again.")
            .setPositiveButton(if (isPlayerWon) "Play Again" else "Try again") { dialog, which ->
                viewModel.resetGame(viewModel.checkIfPlayerHasWon())
            }
            .show()
    }

}