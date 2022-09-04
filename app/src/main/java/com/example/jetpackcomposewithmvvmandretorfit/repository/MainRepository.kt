package com.example.jetpackcomposewithmvvmandretorfit.repository

import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService)
{
    fun getPost(): Flow<List<Post>> = flow {
        emit(apiService.getRequest())
    }.flowOn(Dispatchers.IO)

}