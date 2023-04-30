package com.example.cookitup

import android.R
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.cookitup.databinding.ActivitySettingsBinding
import com.google.android.material.switchmaterial.SwitchMaterial

//TODO WORK IN PROGRESS (NEKAJ NE DELA....)

class SettingsActivity : AppCompatActivity() {
    lateinit var app: MyApplication
    private lateinit var binding: ActivitySettingsBinding

    private lateinit var parentView: View
    private lateinit var themeSwitch: SwitchMaterial
    private lateinit var themeTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //initWidgets()
        loadSharedPreferences()
        initSwitchListener()
    }

    private fun initSwitchListener() {
        themeSwitch.setOnCheckedChangeListener { _, checked ->
            if (checked) app.setCustomTheme(app.DARK_MODE) else app.setCustomTheme(
                app.LIGHT_MODE
            )
            val editor = getSharedPreferences(
                app.PREFERENCES,
                MODE_PRIVATE
            ).edit()
            editor.putString(app.CUSTOM_THEME, app.getCustomTheme())
            editor.apply()
            //updateView()
        }
    }

    private fun loadSharedPreferences() {
        app.sharedPref = getSharedPreferences(app.PREFERENCES, MODE_PRIVATE)
        val theme = app.sharedPref.getString(app.CUSTOM_THEME, app.LIGHT_MODE)

        if (theme != null) {
            app.setCustomTheme(theme)
        }
    }
    /*
    private fun initWidgets() {
        themeTV = findViewById(R.id.LightDarkModeText)
        themeSwitch = findViewById(R.id.modeSwitch)
        parentView = findViewById(R.id.parentView)
    }

    private fun updateView() {
        val black = ContextCompat.getColor(this, R.color.black)
        val white = ContextCompat.getColor(this, R.color.white)
        if (app.getCustomTheme() == app.DARK_MODE) {
            themeTV.setTextColor(white)
            themeTV.text = "Dark"
            parentView.setBackgroundColor(black)
            themeSwitch.isChecked = true
        } else {
            themeTV.setTextColor(black)
            themeTV.text = "Light"
            parentView.setBackgroundColor(white)
            themeSwitch.isChecked = false
        }
    }
 */
}