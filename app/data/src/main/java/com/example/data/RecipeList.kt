package com.example.data

class RecipeList(var name: String) {
    var recipes = mutableListOf<Recipe>()

    fun add(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun sort() {
        recipes.sort()
    }

    fun size(): Int {
        return recipes.size
    }

    override fun toString(): String {
        var recepti: String = "$name:\n"
        for (recept in recipes) {
            recepti += "$recept\n"
        }
        return recepti
    }

    fun findRecipeById(id: String): Recipe? {
        for(recipe in recipes){
            if(recipe.id==id){
                return recipe
            }
        }
        return null
    }

    fun filterByName(searchQuery: String): List<Recipe> {
        return recipes.filter { it.name.contains(searchQuery) }
    }
}