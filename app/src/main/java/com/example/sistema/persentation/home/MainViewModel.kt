package com.example.sistema.persentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistema.common.Resource
import com.example.sistema.persentation.model.FoodResponse
import com.example.sistema.persentation.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _meals = MutableLiveData<Resource<FoodResponse>>()

    val meals : LiveData<Resource<FoodResponse>>
        get() = _meals

    fun getMeals() = viewModelScope.launch {
        _meals.postValue(Resource.loading(null))
        mainUseCase.getMeals().let {
            if (it.isSuccessful) {
                _meals.postValue(Resource.success(it.body()))
            } else {
                _meals.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}