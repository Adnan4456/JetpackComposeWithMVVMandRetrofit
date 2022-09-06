package com.example.jetpackcomposewithmvvmandretorfit.repository

import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.retrofit.PostApi
import javax.inject.Inject

class PostRepositoryImpl
@Inject
constructor(private val api: PostApi)
    : PostRepository{
    override suspend fun getPost(page: Int, limit: Int): List<Post> {
        return api.getRequest(page,limit)
    }
}