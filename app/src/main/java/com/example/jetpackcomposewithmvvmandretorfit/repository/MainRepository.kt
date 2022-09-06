package com.example.jetpackcomposewithmvvmandretorfit.repository

import com.example.jetpackcomposewithmvvmandretorfit.retrofit.PostApi
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiService: PostApi)
{
//    fun getPost(): Flow<List<Post>> = flow {
//        emit(apiService.getRequest())
//    }.flowOn(Dispatchers.IO)
//    fun getPost()

}