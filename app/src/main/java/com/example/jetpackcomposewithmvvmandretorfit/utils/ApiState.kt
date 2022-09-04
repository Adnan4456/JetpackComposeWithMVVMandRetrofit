package com.example.jetpackcomposewithmvvmandretorfit.utils

import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import com.example.jetpackcomposewithmvvmandretorfit.retrofit.ApiService

sealed class ApiState
{
    class Success(val data:List<Post>) :ApiState()
    class Failure (val msg: Throwable) :ApiState()

    object Loading :ApiState()
    object Empty : ApiState()

}