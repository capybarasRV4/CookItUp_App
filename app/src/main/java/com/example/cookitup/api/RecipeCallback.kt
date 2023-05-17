package com.example.cookitup.api

import com.example.data.Recipe

interface RecipeCallback {
    fun onRecipesReceived(recipeList: MutableList<Recipe>)
    fun onFailure(message: String)
}