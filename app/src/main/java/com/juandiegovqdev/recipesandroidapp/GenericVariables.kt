package com.juandiegovqdev.recipesandroidapp

import com.juandiegovqdev.recipesandroidapp.model.Recipe

class GenericVariables {
    companion object {
        var recipes: ArrayList<Recipe> = arrayListOf()
        var selectedRecipe: Recipe = Recipe("", "", "", "")
        val FIRST_URL: String = "http://www.recipepuppy.com/api/?q="
        val SECOND_URL: String = "&p=3"
    }
}