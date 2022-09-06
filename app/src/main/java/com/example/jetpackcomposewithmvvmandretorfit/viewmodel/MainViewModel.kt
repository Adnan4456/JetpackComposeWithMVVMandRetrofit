package com.example.jetpackcomposewithmvvmandretorfit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.jetpackcomposewithmvvmandretorfit.repository.PagingDataSource
import com.example.jetpackcomposewithmvvmandretorfit.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel
@Inject
constructor(private val repo :PostRepository): ViewModel() {

        val post = Pager(
            PagingConfig(pageSize = 10)
        ){
            PagingDataSource(repo)
        }.flow.cachedIn(viewModelScope)

//        fun getPost(): Flow<PagingData<Post>> = Pager(
//            config = PagingConfig(10 , enablePlaceholders = false)
//        ){
//            PagingDataSource(apiService = apiService)
//        }.flow.cachedIn(viewModelScope)

//    val post  =  Pager(
//        config = PagingConfig(10 , enablePlaceholders = false)
//    ){
//        PagingDataSource(apiService = apiService)
//    }.flow.cachedIn(viewModelScope)
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