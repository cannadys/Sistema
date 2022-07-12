package com.example.sistema.remote

import com.example.sistema.persentation.model.DetailResponse
import com.example.sistema.persentation.model.FoodResponse
import com.example.sistema.service.ServiceApi
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val serviceApi: ServiceApi) : MainRepository {

    override suspend fun getMeals(): Response<FoodResponse> {
        return serviceApi.getMeals()
    }

    override suspend fun getDetail(id: Int): Response<DetailResponse> {
        return serviceApi.getDetailsMeals(id)
    }
}