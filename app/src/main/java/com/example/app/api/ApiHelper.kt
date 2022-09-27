package com.example.app.api

import com.example.app.models.Post
import com.example.app.models.PostComment
import retrofit2.Response

/**
 * Created by Bleker Cordova
 */

interface ApiHelper {

    suspend fun getPosts():Response<ArrayList<Post>>
    suspend fun getPostComments(id: Int):Response<ArrayList<PostComment>>
}