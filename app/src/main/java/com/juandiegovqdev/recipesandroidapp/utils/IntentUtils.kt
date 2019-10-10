package com.juandiegovqdev.recipesandroidapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openWebsite(url: String) {
    val openURL = Intent(Intent.ACTION_VIEW)
    openURL.data = Uri.parse(url)
    startActivity(openURL)
}