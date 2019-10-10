package com.juandiegovqdev.recipesandroidapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.juandiegovqdev.recipesandroidapp.GenericVariables
import com.juandiegovqdev.recipesandroidapp.R
import com.juandiegovqdev.recipesandroidapp.adapter.RecipeListViewAdapter
import com.juandiegovqdev.recipesandroidapp.model.Recipe
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    var adapter: RecipeListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeObjects()
        // val url = "http://www.recipepuppy.com/api/?q=omelet&p=3"
        // AsyncTaskHandleJson().execute(url)
    }

    private fun initializeObjects() {
        setContentView(R.layout.activity_main)
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
                    recipe.getString("title"),
                    recipe.getString("href"),
                    recipe.getString("ingredients"),
                    recipe.getString("thumbnail")
                )
            )
        }
        adapter = RecipeListViewAdapter(this, GenericVariables.recipes)
        list.adapter = adapter
        list.setOnItemClickListener { adapterView, view, i, l ->
            GenericVariables.selectedRecipe = GenericVariables.recipes[i]
            val intent = Intent(this, RecipeDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.queryHint = "Search People"
        searchView.setOnQueryTextListener(this)
        searchView.isIconified = false
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if (newText!!.trim() == "" || newText.trim() == null) {
            if (GenericVariables.recipes.equals(null)) {
                no_recipes_text.visibility = View.VISIBLE
                list.visibility = View.INVISIBLE
            }
        } else {
            val url = "http://www.recipepuppy.com/api/?q=${newText.trim()}&p=3"
            AsyncTaskHandleJson().execute(url)
            no_recipes_text.visibility = View.INVISIBLE
            list.visibility = View.VISIBLE
        }

        return true
    }

}
