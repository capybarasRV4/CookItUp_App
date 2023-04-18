package com.example.data

import java.util.*

class Recipe(
    val name: String,
    val type: String,   //this can be ENUM
    val time: Int,
    val complexity: String, //this can be ENUM
    val description: String,
    val ingredients: MutableList<String>,
):Comparable<Recipe> {
    var id: String = UUID.randomUUID().toString().replace("-", "") //random object id

    override fun compareTo(other: Recipe): Int {
        return name.compareTo(other.name)
    }

    fun printDesc(): String{
        val lines = description.split("\n")
        var result = ""
        for ((index, line) in lines.withIndex()) {
            result += "Step ${index + 1}\n$line\n\n"
        }
        return result
    }

    override fun toString(): String {
        return "$id $name $type $time $complexity $description $ingredients"
    }

    /*  printDesc() function
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

        print:
        "
        Step 1
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.

        Step 2
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.

        Step 3
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.

        Step 4
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
        "
    */

    fun getIngredients(): String{
        return ""
    }



}