package com.example.jetpackcomposewithmvvmandretorfit.retrofit

import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getRequest(): List<Post>

}