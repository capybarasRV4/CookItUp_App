package com.example.cookitup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cookitup.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var app: MyApplication

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        //get 3 random recipes:
        ///check if there are 3 recipes total:
        if (app.data.recipes.size < 4) {
            Log.e("Error", "Ni dovolj receptov da bi prikazalo 3 random!")
            Toast.makeText(
                applicationContext,
                "ERROR: ni dovolj receptov, da bi prikazalo 3 random!",
                Toast.LENGTH_LONG
            ).show()
        }
        val random3Set: MutableSet<Int> = mutableSetOf()
        while (random3Set.size < 3) {
            random3Set.add((0..app.data.recipes.size).random())
        }
        val random3List = random3Set.toList()
        val recipe1 = app.data.recipes[random3List[0]]
        val recipe2 = app.data.recipes[random3List[1]]
        val recipe3 = app.data.recipes[random3List[2]]

        //setting values of 3 recipes:
        binding.dishName1.text = recipe1.name
        binding.dishPrepTime1.text = recipe1.time.toString() + " min"

        binding.dishName2.text = recipe2.name
        binding.dishPrepTime2.text = recipe2.time.toString() + " min"

        binding.dishName3.text = recipe3.name
        binding.dishPrepTime3.text = recipe3.time.toString() + " min"

        //on clicks for recipes:
        binding.recipeBox1.setOnClickListener {
            Log.d("OnClick", "Button clicked!")
            val intent = Intent(this@MainActivity, OneRecipeActivity::class.java)
            intent.putExtra("SELECTED_ID", recipe1.id)
            startActivity(intent)
        }

        binding.recipeBox2.setOnClickListener {
            Log.d("OnClick", "Button clicked!")
            val intent = Intent(this@MainActivity, OneRecipeActivity::class.java)
            intent.putExtra("SELECTED_ID", recipe2.id)
            startActivity(intent)
        }

        binding.recipeBox3.setOnClickListener {
            Log.d("OnClick", "Button clicked!")
            val intent = Intent(this@MainActivity, OneRecipeActivity::class.java)
            intent.putExtra("SELECTED_ID", recipe3.id)
            startActivity(intent)
        }

        //other on clicks:
        binding.allRecipesIcon.setOnClickListener {
            val intent = Intent(this, AllRecipesActivity::class.java)
            startActivity(intent)
        }
        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}