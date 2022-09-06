package com.example.jetpackcomposewithmvvmandretorfit.repository

import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.Model.PostResponse

interface PostRepository {

    suspend fun getPost(page: Int, limit: Int): List<Post>

}