package com.example.cookitup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookitup.databinding.ActivityAllRecipesBinding
import com.example.data.Recipe

class AllRecipesActivity : AppCompatActivity() {
    lateinit var app: MyApplication
    private lateinit var binding: ActivityAllRecipesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityAllRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recipes.layoutManager = LinearLayoutManager(this)

        val adapter = RecipeAdapter(app.data.recipes)

        //if entered from SearchActivity
        if(intent.hasExtra("SEARCH_QUERY")){
            val searchQuery = intent.getStringExtra("SEARCH_QUERY")
            val allRecipes = adapter.recipes
            var recipes = mutableListOf<Recipe>()
            for(recipe in allRecipes){
                if(recipe.name.contains(searchQuery!!)){
                    recipes.add(recipe)
                }
            }
            adapter.recipes=recipes
        }

        val editData = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            adapter.notifyDataSetChanged()
        }
        adapter.onClickObject = object:RecipeAdapter.MyOnClick {
            override fun onClick(p0: View?, position:Int) {
                var selected = app.data.recipes[position]
                val intent = Intent(this@AllRecipesActivity, OneRecipeActivity::class.java)
                intent.putExtra("SELECTED_ID", selected.id)
                startActivity(intent)
            }
            /*
            override fun onLongClick(p0: View?, position: Int) {
                val builder = AlertDialog.Builder(this@AllRecipesActivity) //access context from inner class
                //set title for alert dialog
                builder.setTitle("Delete")
                builder.setMessage("Recipe ${app.data.recipes[position]}")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setPositiveButton("Yes"){dialogInterface, which -> //performing positive action
                    Toast.makeText(applicationContext,"clicked yes", Toast.LENGTH_LONG).show()
                    app.data.recipes.removeAt(position)
                    adapter.notifyDataSetChanged()
                    app.saveToFile()
                }
                builder.setNeutralButton("Cancel"){dialogInterface , which -> //performing cancel action
                    Toast.makeText(applicationContext,"clicked cancel\n operation cancel", Toast.LENGTH_LONG).show()
                }
                builder.setNegativeButton("No"){dialogInterface, which -> //performing negative action
                    Toast.makeText(applicationContext,"clicked No", Toast.LENGTH_LONG).show()
                }
                // Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()

            }
             */
        }
        binding.recipes.adapter = adapter
        //adapter.notifyDataSetChanged();

        //navigation buttons on clicks:
        binding.btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSearch.setOnClickListener{
            val intent = Intent(this, SearchRecipeActivity::class.java)
            startActivity(intent)
        }

        binding.btnTimer.setOnClickListener{
            val intent = Intent(this, TimerActivity::class.java)
            startActivity(intent)
        }
    }
}