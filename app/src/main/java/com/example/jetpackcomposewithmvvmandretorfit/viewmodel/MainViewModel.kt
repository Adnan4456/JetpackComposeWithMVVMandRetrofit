package com.example.jetpackcomposewithmvvmandretorfit.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.repository.MainRepository
import com.example.jetpackcomposewithmvvmandretorfit.repository.PagingDataSource
import com.example.jetpackcomposewithmvvmandretorfit.retrofit.ApiService
import com.example.jetpackcomposewithmvvmandretorfit.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject
//constructor(private val mainRepository: MainRepository):
constructor(private val apiService: ApiService):
    ViewModel() {

//        fun getPost(): Flow<PagingData<Post>> = Pager(
//            config = PagingConfig(10 , enablePlaceholders = false)
//        ){
//            PagingDataSource(apiService = apiService)
//        }.flow.cachedIn(viewModelScope)

    val post  =  Pager(
        config = PagingConfig(10 , enablePlaceholders = false)
    ){
        PagingDataSource(apiService = apiService)
    }.flow.cachedIn(viewModelScope)
    /*
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
        } */
}