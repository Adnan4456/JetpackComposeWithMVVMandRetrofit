package com.example.jetpackcomposewithmvvmandretorfit.repository

import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.retrofit.PostApi
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject

class PostRepositoryImpl
@Inject
constructor(private val api: PostApi)
    : PostRepository{

//    var error = 0
    override suspend fun getPost(page: Int, limit: Int): List<Post> {
//        delay(3000)
//        error ++
//        if (error == 2){
//            throw IOException("IO Exception occured.")
//        }
        return api.getRequest(page,limit)
    }
}