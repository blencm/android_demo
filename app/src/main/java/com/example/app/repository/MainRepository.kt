package com.example.app.repository

import com.example.app.api.ApiHelper
import javax.inject.Inject

/**
 * Created by Bleker Cordova
 */

class MainRepository @Inject constructor(
    private val apiHelper:ApiHelper
){

    suspend fun getPosts() = apiHelper.getPosts()

    suspend fun getPostComments(id: Int) = apiHelper.getPostComments(id)

}