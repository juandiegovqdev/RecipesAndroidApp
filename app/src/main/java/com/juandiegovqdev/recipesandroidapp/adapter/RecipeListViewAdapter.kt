package com.juandiegovqdev.recipesandroidapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.juandiegovqdev.recipesandroidapp.R
import com.juandiegovqdev.recipesandroidapp.model.Recipe
import com.squareup.picasso.Picasso

class RecipeListViewAdapter(private var activity: Activity, private var items: ArrayList<Recipe>) :
    BaseAdapter() {
    private class ViewHolder(row: View?) {
        var title: TextView? = null
        var ingredients: TextView? = null
        var thumbnail: ImageView? = null

        init {
            this.title = row?.findViewById(R.id.recipe_title)
            this.ingredients = row?.findViewById(R.id.recipe_ingredients)
            this.thumbnail = row?.findViewById(R.id.recipe_thumbnail)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_view_recipe, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val recipe = items[position]
        viewHolder.title?.text = recipe.title
        viewHolder.ingredients?.text = recipe.ingredients
        if (recipe.thumbnail != null && recipe.thumbnail != "") {
            Picasso.get().load(recipe.thumbnail).into(viewHolder.thumbnail)
        } else {
            viewHolder.thumbnail?.visibility = View.GONE
        }
        return view
    }

    override fun getItem(i: Int): Recipe {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}