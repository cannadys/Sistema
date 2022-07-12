package com.example.sistema.persentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.sistema.R
import com.example.sistema.persentation.model.ListMeals
import kotlinx.android.synthetic.main.item_list_meals.view.*

class MainAdapter(
    private val listMeals: List<ListMeals>,
    private val clickProduct: (ListMeals) -> Unit
) : RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder {
        return MainAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_meals, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {
        val data = this.listMeals.getOrNull(position) ?: return
        holder.bind(data)
        holder.itemView.constraint_content.setOnClickListener {
            this.clickProduct(data)
        }
    }

    override fun getItemCount(): Int = listMeals.size

    inner class MainAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(meals: ListMeals) {
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            Glide
                .with(itemView.context)
                .load(meals.strMealThumb)
                .placeholder(circularProgressDrawable)
                .into(itemView.iv_meals)
            itemView.tv_meals.text = meals.strMeals
        }

    }
}