package com.example.app.models

/**
 * Created by Bleker Cordova
 */

data class Post(
    val id: Int?=0,
    val userId: Int?=0,
    val title: String?="",
    val body: String?=""
)

data class PostComment(
    val id: Int?=0,
    val postId: Int?=0,
    val name: String?="",
    val email: String?="",
    val body: String?=""
)