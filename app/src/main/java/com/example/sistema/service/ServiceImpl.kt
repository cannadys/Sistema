package com.example.sistema.service

import com.example.sistema.persentation.model.FoodResponse
import com.example.sistema.service.ServiceApi
import com.example.sistema.service.ServiceHelper
import retrofit2.Response
import javax.inject.Inject

class ServiceImpl @Inject constructor(private val serviceApi: ServiceApi) : ServiceHelper {
    override suspend fun getMeals(): Response<FoodResponse> {
        return serviceApi.getMeals()
    }

}