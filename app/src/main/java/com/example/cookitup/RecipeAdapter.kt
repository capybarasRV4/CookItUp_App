package com.example.cookitup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.RecipeList
import com.squareup.picasso.Picasso

class RecipeAdapter(private val data:RecipeList) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    lateinit var onClickObject:MyOnClick

    interface MyOnClick {
        fun onClick(p0: View?, position:Int)
        fun onLongClick(p0: View?, position:Int)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        //TODO ~ need xml
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*
        val recipes = data.recipes[position]

        Picasso.get().load("https://icons.iconarchive.com/icons/iconarchive/fat-sugar-food/256/Pizza-icon.png").into(holder.imageView);

        holder.name.text = recipes.name
        holder.time.text = recipes.time

        holder.line.setOnClickListener { p0 ->
            onClickObject.onClick(p0, holder.bindingAdapterPosition) //Action from Activity
        }
        holder.line.setOnLongClickListener { p0 ->
            onClickObject.onLongClick(p0, holder.bindingAdapterPosition)
            true
        }
         */

    }
    override fun getItemCount(): Int {
        return data.size()
    }
}