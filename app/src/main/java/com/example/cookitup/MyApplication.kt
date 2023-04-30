package com.example.cookitup

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.data.Recipe
import com.example.data.RecipeList
import com.google.gson.Gson
import java.io.File
import io.github.serpro69.kfaker.Faker
import org.apache.commons.io.FileUtils
import java.io.IOException
import java.util.*

const val MY_FILE_NAME = "mydata.json"
const val MY_SP_FILE_NAME = "myshared.data"

class MyApplication: Application() {
    lateinit var data: RecipeList
    private lateinit var gson: Gson
    private lateinit var file: File
    lateinit var sharedPref: SharedPreferences
    val faker = Faker()

    var PREFERENCES: String = "preferences"

    var CUSTOM_THEME: String = "customTheme"
    var LIGHT_MODE: String = "lightTheme"
    var DARK_MODE: String = "darkTheme"

    private lateinit var customTheme: String

    fun getCustomTheme(): String {
        return customTheme
    }

    fun setCustomTheme(customTheme: String) {
        this.customTheme = customTheme
    }

    override fun onCreate() {
        super.onCreate()
        gson = Gson()
        file = File(filesDir, MY_FILE_NAME)
        initData()
        initShared()
        if (!containsID()) {
            saveID(UUID.randomUUID().toString().replace("-", ""))
        }
    }
    fun initShared() {
        sharedPref = getSharedPreferences( MY_SP_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun saveID(id:String) {
        with (sharedPref.edit()) {
            putString("ID", id)
            apply()
        }
    }
    fun containsID():Boolean {
        return sharedPref.contains("ID")
    }
    fun getID(): String? {
        return sharedPref.getString("ID","DefaultNoData")
    }

    fun saveToFile() {
        try {
            FileUtils.writeStringToFile(file, gson.toJson(data))
        } catch (e: IOException) {
            println("exception")
        }
    }

    private fun initData() {
        data = try { //www
            gson.fromJson(FileUtils.readFileToString(file), RecipeList::class.java)
        } catch (e: IOException) {
            RecipeList("Recepti:")
        }
        /*
        //data.recipes.clear()
        val test = mutableListOf<String>()
        test.add("jajce")

        if (data.size() < 100) {
            for (i in 1..100) {
                data.add(
                    Recipe("title $i", "breakfast", 60, "hard", faker.food.descriptions(), test)
                )
                saveToFile()
            }
        }
         */

    }
}