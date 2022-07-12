package com.example.sistema.persentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.sistema.R
import com.example.sistema.common.Status
import com.example.sistema.persentation.detail.DetailActivity
import com.example.sistema.persentation.model.ListMeals
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.observeViewModel()
        mainViewModel.getMeals()
    }

    private fun observeViewModel() {
        this.mainViewModel.meals.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    if (it.data != null) {
                        this.handleDataMeals(it.data.meals)
                    }
                }
                Status.ERROR -> {
                    hideLoading()
                }
            }
        })
    }

    private fun handleDataMeals(data: List<ListMeals>?) {
        rv_meals.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        rv_meals.adapter = data?.let {
            MainAdapter(
                listMeals = it,
                clickProduct = this::handleClickProduct
            )
        }
    }

    private fun handleClickProduct(listMeals: ListMeals) {
        DetailActivity.moveToDetailMeals(this, listMeals)
    }

    private fun showLoading() {
        pb_meals_list.visibility = View.VISIBLE
        rv_meals.visibility = View.GONE
    }

    private fun hideLoading() {
        pb_meals_list.visibility = View.GONE
        rv_meals.visibility = View.VISIBLE
    }
}