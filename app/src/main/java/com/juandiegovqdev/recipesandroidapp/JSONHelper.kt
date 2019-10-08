package com.juandiegovqdev.recipesandroidapp

import org.json.JSONException
import org.json.JSONObject

fun parse(url: String): JSONObject? {
    var jsonObject: JSONObject? = null
    try {
        jsonObject = JSONObject(url)
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return jsonObject
}