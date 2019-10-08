package com.juandiegovqdev.recipesandroidapp.utils

fun String.getIngredientsFromString(): ArrayList<String> {
    val ing = this.split(",")
    val res: ArrayList<String> = arrayListOf()
    for (i in ing) {
        res.add(i.trim())
    }
    return res
}