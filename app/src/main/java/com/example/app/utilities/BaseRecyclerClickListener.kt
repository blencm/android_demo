package com.example.app.utilities

/**
 * Created by Bleker Cordova
 */

interface BaseRecyclerClickListener<T> {

    fun onClick(data: T, position: Int)

}