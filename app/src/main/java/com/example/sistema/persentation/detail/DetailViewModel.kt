package com.example.sistema.persentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistema.common.Resource
import com.example.sistema.persentation.model.DetailResponse
import com.example.sistema.persentation.model.FoodResponse
import com.example.sistema.persentation.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel()  {

    val id = MutableLiveData<Int>()
    private val _detailMeals = MutableLiveData<Resource<DetailResponse>>()

    val detailMeals : LiveData<Resource<DetailResponse>>
        get() = _detailMeals

    fun getDetailMeals() = viewModelScope.launch {
        _detailMeals.postValue(Resource.loading(null))
        id.value?.let {
            mainUseCase.getDetails(it).let {
                if (it.isSuccessful) {
                    _detailMeals.postValue(Resource.success(it.body()))
                } else _detailMeals.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}