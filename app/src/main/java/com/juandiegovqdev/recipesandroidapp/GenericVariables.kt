package com.juandiegovqdev.recipesandroidapp

import com.juandiegovqdev.recipesandroidapp.model.Recipe

class GenericVariables {
    companion object {
        var recipes: ArrayList<Recipe> = arrayListOf()
        var selectedRecipe: Recipe = Recipe("", "", "", "")
    }
}