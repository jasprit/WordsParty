package com.shift.wordsparty.ui

import android.os.Bundle
import android.util.Log
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

        Log.d("SDasdsad", viewModel.isPlayerLost.value.toString());
    }


    private fun setObservables() {
        viewModel.guessedAnswer.observe(this, Observer {
            if (it.length == 3 && viewModel.checkIfPlayerHasWon()) {
                showDialog()
            } else if (it.length == 3) {
                viewModel.isPlayerLost.value = true
            }
        })
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(this)
            .setCancelable(false)
            .setTitle("Hurray")
            .setMessage("Can't wait to play more.")
            .setPositiveButton("Play Again") { dialog, which ->
                viewModel.resetGame(true)
                viewModel.isPlayerLost.value = false
            }
            .show()
    }

}