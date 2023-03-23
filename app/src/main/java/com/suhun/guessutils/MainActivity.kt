package com.suhun.guessutils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.suhun.guessutils.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    val tag = MainActivity::class.java.simpleName
    val secretNumber:SecretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Reset Game")
                .setMessage("Are you sure?").setPositiveButton("Ok", { dialog, view ->
                    secretNumber.resetAll()
                    binding.contentLayout.userInputText.text = null
                    binding.contentLayout.guessCounterView.text = "0"
                    Toast.makeText(this, "Suecess", Toast.LENGTH_LONG)
                })
                .setNeutralButton("Cancel", null)
                .show()
        }

        binding.contentLayout.guessButton.setOnClickListener { view ->
            val userInput: Int = binding.contentLayout.userInputText.text.toString().toInt()
            val message: String = secretNumber.verifyResult(resources, userInput)
            val bingo = if (secretNumber.verify(userInput) == 0) true else false

            binding.contentLayout.guessCounterView.text = "${secretNumber.guessCounter.toString()} times"
            AlertDialog.Builder(this)
                .setTitle("Guess Result")
                .setMessage(message).setPositiveButton("Ok", {dialog, view->
                    if(bingo){
                        val intent:Intent = Intent(this, RecordActivity::class.java)
                        intent.putExtra("COUNT", secretNumber.guessCounter)
                        startActivity(intent)
                    }else{
                        binding.contentLayout.userInputText.text = null
                    }
                })
                .show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}