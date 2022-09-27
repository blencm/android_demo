package com.example.app.api

import com.example.app.models.Post
import com.example.app.models.PostComment
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Bleker Cordova
 */

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper{

    override suspend fun getPosts(): Response<ArrayList<Post>>  = apiService.getPosts()

    override suspend fun getPostComments(id: Int): Response<ArrayList<PostComment>>  = apiService.getPostComments(id)

}