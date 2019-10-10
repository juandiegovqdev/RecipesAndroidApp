package com.juandiegovqdev.recipesandroidapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.juandiegovqdev.recipesandroidapp.GenericVariables
import com.juandiegovqdev.recipesandroidapp.R
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.content_recipe_details.*

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeObjects()
    }

    private fun initializeObjects() {
        setContentView(R.layout.activity_recipe_details)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = GenericVariables.selectedRecipe.title
        recipe_title.text = GenericVariables.selectedRecipe.title
        recipe_ingredients.text = GenericVariables.selectedRecipe.ingredients
        recipe_website.text = GenericVariables.selectedRecipe.href
    }

    fun initializeListeners() {

    }

}
