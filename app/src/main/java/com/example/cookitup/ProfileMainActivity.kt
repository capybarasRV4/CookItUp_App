package com.example.cookitup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cookitup.databinding.ActivityProfileMainBinding
import com.example.cookitup.databinding.ActivityTimerBinding

class ProfileMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileMainBinding
    private lateinit var app: MyApplication
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var usernameText: TextView
    private lateinit var emailText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityProfileMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        usernameText = findViewById(R.id.username_text)
        emailText = findViewById(R.id.email_text)

        val loginButton: Button = findViewById(R.id.login_button)
        val registerButton: Button = findViewById(R.id.register_button)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.imageButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val isLoggedIn = sharedPreferences.contains("username")
        if (isLoggedIn) {
            val username = sharedPreferences.getString("username", "")
            val email = sharedPreferences.getString("email", "")

            usernameText.text = "Username: $username"
            emailText.text = "Email: $email"
        } else {
            usernameText.text = "Not logged in"
            emailText.text = ""
        }
    }
}