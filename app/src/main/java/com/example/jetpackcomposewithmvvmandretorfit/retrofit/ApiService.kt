package com.example.jetpackcomposewithmvvmandretorfit.retrofit

import android.graphics.pdf.PdfDocument
import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")

    suspend fun getRequest(
        @Query("page")page:Int,
        @Query("limit") limit : Int
    ): List<Post>

}