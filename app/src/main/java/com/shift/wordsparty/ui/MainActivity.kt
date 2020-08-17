package com.shift.wordsparty.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shift.wordsparty.R
import com.shift.wordsparty.databinding.ActivityMainBinding
import com.shift.wordsparty.extentions.playSound

class MainActivity : AppCompatActivity() {

     lateinit var viewModel:MainViewModel
     private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding?.lifecycleOwner = this
        binding?.viewmodel = viewModel


        playSound(this, R.raw.abc)
        viewModel.resetGame(true)
        setObservables()
    }


    private fun setObservables() {
        viewModel.guessedAnswer.observe(this, Observer {
            if (it.length == 3 && viewModel.checkIfPlayerHasWon()) {
                viewModel.isPlayerWon.value = true
                showDialog()
                playSound(this, R.raw.victory)
            } else if (it.length == 3) {
                viewModel.isPlayerLost.value = true
                playSound(this, R.raw.error)
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
            }
            .show()
    }

}