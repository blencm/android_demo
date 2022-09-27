package com.example.app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.app.R
import com.example.app.models.Post
import com.example.app.ui.adapters.CommentAdapter
import com.example.app.ui.adapters.PostAdapter
import com.example.app.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Bleker Cordova
 */

@AndroidEntryPoint
open class BaseFragment: Fragment() {
    val mainViewModel: MainViewModel by activityViewModels()
    lateinit var postAdapter: PostAdapter
    lateinit var commentAdapter: CommentAdapter

    //Add view fragments
    protected open fun addViewFragmentArgs(
        containerId: Int,
        clazz: Class<out Fragment>,
        args: Bundle?
    ) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(containerId, clazz, args)
            .commitAllowingStateLoss()

    }
    protected open fun addViewFragment(
        containerId: Int,
        fragment: Fragment
    ) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment).commit()
    }
    //Add args
    fun createArgs(post: Post): Bundle {
        return DetailFragmentArgs.Builder(
            post.id!!,
            post.userId!!,
            post.title!!,
            post.body!!
        ).build().toBundle()
    }
}