package com.example.app.ui.fragments

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import com.example.app.R
import com.example.app.databinding.FragmentItemListBinding
import com.example.app.models.Post
import com.example.app.other.Status
import com.example.app.ui.adapters.PostAdapter
import com.example.app.utilities.BaseRecyclerClickListener
import com.example.app.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Bleker Cordova
 */

@AndroidEntryPoint
class ItemsFragment : BaseFragment() {

    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title =  getString(R.string.posts)
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        recyclerViewSetup()
        return binding.root
    }
    //Setup recyclerView
    private fun recyclerViewSetup() {
        postAdapter = PostAdapter(object : BaseRecyclerClickListener<Post> {
            override fun onClick(data: Post, position: Int) {
                addViewFragmentArgs(R.id.container, DetailFragment::class.java, createArgs(data))
            }
        })

        binding.recyclerView.setHasFixedSize(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = postAdapter

        mainViewModel.res.observe(this.viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    it.data.let { res ->
                        res?.let { it1 -> postAdapter.setData(it1) }
                    }
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }
        }
    }
}