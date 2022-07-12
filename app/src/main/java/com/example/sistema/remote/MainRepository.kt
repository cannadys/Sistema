package com.example.sistema.remote

import com.example.sistema.persentation.model.DetailResponse
import com.example.sistema.persentation.model.FoodResponse
import retrofit2.Response

interface MainRepository {

    suspend fun getMeals() : Response<FoodResponse>

    suspend fun getDetail(id: Int): Response<DetailResponse>
}