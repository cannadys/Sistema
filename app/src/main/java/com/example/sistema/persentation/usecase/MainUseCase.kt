package com.example.sistema.persentation.usecase

import com.example.sistema.persentation.model.DetailResponse
import com.example.sistema.persentation.model.FoodResponse
import com.example.sistema.remote.MainRepository
import retrofit2.Response
import javax.inject.Inject

class MainUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend fun getMeals() : Response<FoodResponse> {
        return mainRepository.getMeals()
    }

    suspend fun getDetails(id: Int) : Response<DetailResponse> {
        return mainRepository.getDetail(id)
    }
}