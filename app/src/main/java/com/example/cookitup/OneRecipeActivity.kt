package com.example.cookitup

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.cookitup.databinding.ActivityOneRecipeBinding

class OneRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOneRecipeBinding
    lateinit var app: MyApplication
    lateinit var adapter: IngredientAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication

        binding = ActivityOneRecipeBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        if (intent.hasExtra("SELECTED_ID")) {
            val id = intent.getStringExtra("SELECTED_ID")
            if (id != null) {
                val recipe = app.data.findRecipeById(id)
                if (recipe != null) {
                    binding.recipeTitle.text = recipe.name
                    binding.recipeTime.text = recipe.time.toString() + " min"
                    binding.recipeDescription.text = recipe.description

                    adapter = IngredientAdapter(recipe.ingredients, this)
                    binding.recyclerview.adapter = adapter
                } else {
                    Log.d("OneRecipe", "Recipe with this ID doesn't exist!")
                    finish()
                }
            } else {
                Log.d("OneRecipe", "Error converting SELECTED_ID!")
                finish()
            }
        } else {
            Log.d("OneRecipe", "Error getting SELECTED_ID!")
            finish()
        }
        binding.imageButton5.setOnClickListener {
            finish()
        }
    }
}
