package com.example.cookitup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookitup.databinding.ActivityAllRecipesBinding
import com.example.cookitup.databinding.ActivityProfileMainBinding
import com.example.cookitup.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    lateinit var app: MyApplication
    private lateinit var binding: ActivityRegistrationBinding

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var usernameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        usernameInput = findViewById(R.id.editTextTextPersonName)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.editTextTextPassword)

        val signUpButton: Button = findViewById(R.id.sign_up_btn)
        signUpButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Save username, email, and password to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            editor.putString("email", email)
            editor.putString("password", password)
            editor.apply()

            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}






