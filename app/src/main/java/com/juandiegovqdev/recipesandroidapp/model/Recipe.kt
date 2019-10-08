package com.juandiegovqdev.recipesandroidapp.model

data class Recipe(
    var title: String,
    var href: String,
    var ingredients: ArrayList<String>,
    var thumbnail: String
)