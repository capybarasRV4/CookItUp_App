package com.example.cookitup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.cookitup.databinding.ActivitySearchRecipeBinding

class SearchRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchRecipeBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        //on search:
        binding.buttonSearchRecipes.setOnClickListener{
            if (binding.searchQueryInput.text.isEmpty()) {
                val msg="Input search query before trying to search"
                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this@SearchRecipeActivity, AllRecipesActivity::class.java)
                Log.i("OnClick", binding.searchQueryInput.text.toString())
                intent.putExtra("SEARCH_QUERY", binding.searchQueryInput.text.toString())
                startActivity(intent)
            }
        }

        //navigation buttons on clicks:
        binding.allRecipesIcon.setOnClickListener {
            val intent = Intent(this, AllRecipesActivity::class.java)
            startActivity(intent)
        }
        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnTimer.setOnClickListener{
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
    }
}