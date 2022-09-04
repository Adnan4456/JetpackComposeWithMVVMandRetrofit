package com.example.jetpackcomposewithmvvmandretorfit.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposewithmvvmandretorfit.repository.MainRepository
import com.example.jetpackcomposewithmvvmandretorfit.retrofit.ApiService
import com.example.jetpackcomposewithmvvmandretorfit.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository):
    ViewModel() {

    val response : MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        //Now collect this into your main activity
        getPost()
    }


        fun  getPost() = viewModelScope.launch {
            mainRepository.getPost()
                .onStart {

                    response.value = ApiState.Loading

                }.catch { it ->
                    response.value = ApiState.Failure(it)
                }
                .collect{
                    response.value = ApiState.Success(it)
                }
        }
}