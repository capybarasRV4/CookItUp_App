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

class AllRecipesActivity : AppCompatActivity() {
    lateinit var app: MyApplication
    private lateinit var binding: ActivityAllRecipesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityAllRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recipes.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeAdapter(app.data)

        val editData = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            adapter.notifyDataSetChanged()
        }
        adapter.onClickObject = object:RecipeAdapter.MyOnClick {
            override fun onClick(p0: View?, position:Int) {
                //TODO ~ need AddActivity
            }

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
        }
        binding.recipes.adapter = adapter
        //adapter.notifyDataSetChanged();

        //TODO ~ add buttons
    }
}