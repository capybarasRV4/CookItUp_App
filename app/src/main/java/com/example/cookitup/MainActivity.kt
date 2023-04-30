package com.example.cookitup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cookitup.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var app: MyApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app=application as MyApplication

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.recipeBox.setOnClickListener{
            Log.d("OnClick", "Button clicked!")
            val intent = Intent(this@MainActivity, OneRecipeActivity::class.java)
            intent.putExtra("SELECTED_ID", "123") //potem bomo poslali id poslanega recepta, ki bo izbran (recepti bodo shranjeni v MyApplication)
            startActivity(intent)
        }
        binding.allRecipesIcon.setOnClickListener {
            val intent = Intent(this, AllRecipesActivity::class.java)
            startActivity(intent)
        }
        binding.imageButton3.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}