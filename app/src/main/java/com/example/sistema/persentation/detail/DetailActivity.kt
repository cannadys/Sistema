package com.example.sistema.persentation.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.sistema.R
import com.example.sistema.common.Status
import com.example.sistema.persentation.model.ListMeals
import com.example.sistema.persentation.model.Meals
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import java.io.Serializable

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private var listMeals: ListMeals? = null
    private val detailViewModel: DetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        listMeals = intent.getSerializableExtra(LIST_MEAL_FOR_DETAIL) as ListMeals?

        detailViewModel.id.value = listMeals?.idMeal?.toInt()
        detailViewModel.getDetailMeals()
        observeViewModel()
    }

    private fun observeViewModel() {
        this.detailViewModel.detailMeals.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    if (it.data != null) {
                        this.handleDataDetailMeals(it.data.meals?.get(0))
                    }
                }
                Status.ERROR -> {
                    hideLoading()
                }
            }
        })
    }

    private fun handleDataDetailMeals(meals: Meals?) {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide
            .with(this)
            .load(meals?.strMealThumb)
            .placeholder(circularProgressDrawable)
            .into(iv_detail_foods)
        tv_detail_foods.text = meals?.strMeal
        tv__subtitle_detail_foods.text = meals?.strInstructions
    }

    private fun showLoading() {
        pb_meals_detail.visibility = View.VISIBLE
        constraint_detail.visibility = View.GONE
    }

    private fun hideLoading() {
        pb_meals_detail.visibility = View.GONE
        constraint_detail.visibility = View.VISIBLE
    }

    companion object{
        const val LIST_MEAL_FOR_DETAIL = "listMealForDetail"

        fun moveToDetailMeals(context: Activity, listMeals: ListMeals) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.apply {
                putExtra(LIST_MEAL_FOR_DETAIL, listMeals as Serializable)
            }
            context.startActivity(intent)
        }
    }
}