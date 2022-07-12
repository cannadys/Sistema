package com.example.sistema.persentation.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodResponse(
    @SerializedName("meals")
    val meals: List<ListMeals>?
)

data class ListMeals(
    @SerializedName("strMeal")
    val strMeals: String?,
    @SerializedName("strMealThumb")
    val strMealThumb: String?,
    @SerializedName("idMeal")
    val idMeal: String?
) : Serializable
