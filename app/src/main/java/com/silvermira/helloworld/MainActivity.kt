package com.silvermira.helloworld

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.silvermira.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private var count = 0
            set(value) {
                field = value;
                binding.clickButton.text = countText();
            };

    private var name = ""
    set(value) {
        field = value
        binding.welcomeText.text = welcomeText()
    }

    private fun welcomeText(): String {
        return String.format(resources.getString(R.string.welcome), name)
    }

    private fun countText(): kotlin.String {
        return String.format(resources.getString(R.string.num_clicks), count);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        count = 0
        name = ""
        binding.clickButton.setOnClickListener {
            count++;
        }
        binding.rollDiceButton.setOnClickListener{
            rollDice();
        }
        binding.updateNameButton.setOnClickListener {
            updateName();
        }
    }

    private fun updateName(view : View) {
        name = binding.nameInput.text.toString()

        binding.nameInput.text.clear();
        binding.nameInput.clearFocus()

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //imm.showSoftInput(binding.nameInput, 0)
       // imm.hideSoftInputFromWindow( view, 0)
    }

    private fun rollDice() {
        val randomInt = (1..6).random();
        binding.diceImage.setImageResource(getDrawableByNumber(randomInt));
    }

    private fun getDrawableByNumber(randomInt: Int): Int {
        return when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}