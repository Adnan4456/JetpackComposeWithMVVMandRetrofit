package com.example.jetpackcomposewithmvvmandretorfit.retrofit

import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.Model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    suspend fun getRequest(
        @Query("page")page:Int,
        @Query("limit") limit : Int
    ): List<Post>
}