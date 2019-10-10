package com.juandiegovqdev.recipesandroidapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.juandiegovqdev.recipesandroidapp.GenericVariables
import com.juandiegovqdev.recipesandroidapp.R
import com.juandiegovqdev.recipesandroidapp.utils.openWebsite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.content_recipe_details.*

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeObjects()
        initializeListeners()
    }

    private fun initializeObjects() {
        setContentView(R.layout.activity_recipe_details)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.title = GenericVariables.selectedRecipe.title
        if (GenericVariables.selectedRecipe.title != null && GenericVariables.selectedRecipe.title != "") {
            recipe_title.text = GenericVariables.selectedRecipe.title
        } else {
            recipe_title_card_view.visibility = View.GONE
        }
        if (GenericVariables.selectedRecipe.ingredients != null && GenericVariables.selectedRecipe.ingredients != "") {
            recipe_ingredients.text = GenericVariables.selectedRecipe.ingredients
        } else {
            recipe_ingredients_card_view.visibility = View.GONE
        }
        if (GenericVariables.selectedRecipe.href != null && GenericVariables.selectedRecipe.href != "") {
            recipe_website.text = GenericVariables.selectedRecipe.href
        } else {
            recipe_website_card_view.visibility = View.GONE
        }
        if (GenericVariables.selectedRecipe.thumbnail != null && GenericVariables.selectedRecipe.thumbnail != "") {
            Picasso.get().load(GenericVariables.selectedRecipe.thumbnail).into(recipe_thumbnail)
        } else {
            recipe_thumbnail_card_view.visibility = View.GONE
        }
    }

    fun initializeListeners() {
        recipe_website_card_view.setOnClickListener {
            this.openWebsite(GenericVariables.selectedRecipe.href)
        }
    }

}
