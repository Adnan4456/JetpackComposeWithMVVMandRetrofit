package com.example.jetpackcomposewithmvvmandretorfit.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackcomposewithmvvmandretorfit.Model.Post
import retrofit2.HttpException
import java.io.IOException

class PagingDataSource constructor(private val repository: PostRepository)
    : PagingSource<Int, Post>() {

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {

        return state.anchorPosition?. let{ position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1)?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        //if it has page key then pass it otherwise pass 1.
        val page = params.key ?: 1
        return try {
//            val response = apiService.getRequest(page, params.loadSize)
            val response = repository.getPost(page,10)
            LoadResult.Page(
                response,
                prevKey = if (page ==1 ) null else page-1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        }
        catch (e: IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }

    }

}