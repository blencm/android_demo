package com.example.app.api

import com.example.app.models.Post
import com.example.app.models.PostComment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Bleker Cordova
 */

interface ApiService{

    @GET("posts")
    suspend fun getPosts():Response<ArrayList<Post>>

    @GET("posts/{id}/comments")
    suspend fun getPostComments(@Path("id") groupId: Int): Response<ArrayList<PostComment>>
}