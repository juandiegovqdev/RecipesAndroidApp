package com.juandiegovqdev.recipesandroidapp

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.juandiegovqdev.recipesandroidapp.model.Recipe
import com.juandiegovqdev.recipesandroidapp.utils.getIngredientsFromString
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "http://www.recipepuppy.com/api/?q=omelet&p=3"
        // val i = parse("http://www.recipepuppy.com/api/?q=omelet&p=3")

        // Toast.makeText(this, getJsonFromURL(url).toString(), Toast.LENGTH_SHORT).show()

        AsyncTaskHandleJson().execute(url)
    }

    @SuppressLint("StaticFieldLeak")
    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            val text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use {
                    it.reader().use { reader ->
                        reader.readText()
                    }
                }
            } finally {
                connection.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    @SuppressLint("NewApi")
    private fun handleJson(jsonString: String?) {
        val json = JSONObject(jsonString)
        val results: JSONArray = json.getJSONArray("results")
        GenericVariables.recipes.clear()
        for (n in 0 until results.length()) {
            val recipe = results.getJSONObject(n)
            GenericVariables.recipes.add(
                Recipe(
                    recipe.getString("href"),
                    recipe.getString("ingredients"),
                    recipe.getString("thumbnail").getIngredientsFromString(),
                    recipe.getString("title")
                )
            )
        }
        Toast.makeText(this, GenericVariables.recipes[5].toString(), Toast.LENGTH_SHORT).show()
    }
}
