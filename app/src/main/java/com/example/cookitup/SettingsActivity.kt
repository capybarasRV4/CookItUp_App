package com.example.cookitup

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.cookitup.databinding.ActivitySettingsBinding
import com.google.android.material.switchmaterial.SwitchMaterial


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var app: MyApplication
    private var themeSwitch: SwitchMaterial? = null
    private var themeTV: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initWidgets()
        loadSharedPreferences()
        initSwitchListener()

        binding.imageButton5.setOnClickListener {
            finish()
        }
    }
    private fun initWidgets() {
        themeTV = binding.LightDarkModeText
        themeSwitch = binding.modeSwitch
    }

    private fun loadSharedPreferences() {
        val sharedPreferences = getSharedPreferences(app.PREFERENCES, MODE_PRIVATE)
        val theme = sharedPreferences.getString(app.CUSTOM_THEME, app.LIGHT_THEME)
        app.setCustomTheme(theme)
        updateView()
    }

    private fun initSwitchListener() {
        themeSwitch!!.setOnCheckedChangeListener { _, checked ->
            if (checked) app.setCustomTheme(app.DARK_THEME) else app.setCustomTheme(
                app.LIGHT_THEME
            )
            val editor = getSharedPreferences(
                app.PREFERENCES,
                MODE_PRIVATE
            ).edit()
            editor.putString(app.CUSTOM_THEME, app.getCustomTheme())
            editor.apply()
            updateView()
        }
    }

    private fun updateView() {
        val black = ContextCompat.getColor(this, R.color.black)
        val white = ContextCompat.getColor(this, R.color.white)
        if (app.getCustomTheme().equals(app.DARK_THEME)) {
            themeTV!!.setTextColor(white)
            themeTV!!.text = "Dark"
            themeSwitch!!.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            themeTV!!.setTextColor(black)
            themeTV!!.text = "Light"
            themeSwitch!!.isChecked = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}