package com.example.cookitup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Recipe
import com.squareup.picasso.Picasso

class RecipeAdapter(var recipes:MutableList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    lateinit var onClickObject:MyOnClick

    interface MyOnClick {
        fun onClick(p0: View?, position:Int)
        //fun onLongClick(p0: View?, position:Int)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView9)
        val name: TextView = itemView.findViewById(R.id.recipe_name)
        val prep: TextView = itemView.findViewById(R.id.recipe_prep)
        val line: CardView = itemView.findViewById(R.id.cvLine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipes = recipes[position]

        if (recipes.name.contains("pica") || recipes.name.contains("pizza") || recipes.name.contains("pice") || recipes.name.contains("pizze")) {
            Picasso.get().load("https://icons.iconarchive.com/icons/iconarchive/fat-sugar-food/512/Pizza-icon.png").into(holder.imageView)
        } else if (recipes.name.contains("kruh")) {
            Picasso.get().load("https://icons.iconarchive.com/icons/google/noto-emoji-food-drink/512/32371-bread-icon.png").into(holder.imageView)
        }
        else {
            Picasso.get().load("https://icons.iconarchive.com/icons/aniket-suvarna/box-regular/512/bx-dish-icon.png").into(holder.imageView)
        }

        holder.name.text = recipes.name
        holder.prep.text = "Cas priprave: " + recipes.time

        holder.line.setOnClickListener { p0 ->
            onClickObject.onClick(p0, holder.bindingAdapterPosition) //Action from Activity
        }
        /*
        holder.line.setOnLongClickListener { p0 ->
            onClickObject.onLongClick(p0, holder.bindingAdapterPosition)
            true
        }
         */
    }
    override fun getItemCount(): Int {
        return recipes.size
    }
}