package com.example.sistema.service

import com.example.sistema.persentation.model.FoodResponse
import retrofit2.Response

interface ServiceHelper {
    suspend fun getMeals() : Response<FoodResponse>
}