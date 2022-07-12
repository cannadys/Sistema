package com.example.sistema.service

import com.example.sistema.persentation.model.DetailResponse
import com.example.sistema.persentation.model.FoodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("api/json/v1/1/filter.php?c=Seafood")
    suspend fun getMeals() : Response<FoodResponse>

    @GET("api/json/v1/1/lookup.php?")
    suspend fun getDetailsMeals(@Query("i") query: Int) : Response<DetailResponse>
}