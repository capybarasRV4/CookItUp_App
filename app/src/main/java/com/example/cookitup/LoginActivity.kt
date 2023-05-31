package com.example.cookitup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)

        val loginButton: Button = findViewById(R.id.sign_up_btn)
        loginButton.setOnClickListener {
            val savedUsername = sharedPreferences.getString("username", "")
            val savedPassword = sharedPreferences.getString("password", "")

            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username == savedUsername && password == savedPassword) {
                // Login successful
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                // TODO: Start your desired activity
            } else {
                // Login failed
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        val signUpButton: Button = findViewById(R.id.register_btn)
        signUpButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}