package com.example.cookitup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cookitup.databinding.ActivityMainBinding
import com.example.cookitup.databinding.ActivityOneRecipeBinding

class OneRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOneRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOneRecipeBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
    }
}