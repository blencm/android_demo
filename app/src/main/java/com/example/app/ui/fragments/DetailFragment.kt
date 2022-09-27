package com.example.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.databinding.FragmentDetailBinding
import com.example.app.models.Post
import com.example.app.other.Status
import com.example.app.ui.adapters.CommentAdapter
import com.example.app.ui.adapters.PostAdapter
import com.example.app.utilities.BaseRecyclerClickListener
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.plugin.util.capitalize


/**
 * Created by Bleker Cordova
 */

@AndroidEntryPoint
class DetailFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var args: DetailFragmentArgs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        args =
            DetailFragmentArgs.fromBundle(
                requireArguments()
            )
        initView()
        recyclerViewSetup()
        onClickListener()
        return binding.root
    }
    //Init view
    private fun initView() {
        //Toolbar
        binding.toolbar.toolbar.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar.toolbar.title = args.title.capitalize()
        //Content
        binding.title.text = args.title.capitalize()
        binding.content.text = args.body.capitalize()
    }
    private fun onClickListener(){
        binding.toolbar.toolbar.setNavigationOnClickListener(View.OnClickListener {
            addViewFragment(R.id.container, ItemsFragment())
        })
    }
    //Setup recyclerView
    private fun recyclerViewSetup() {
        mainViewModel.getPostComments(args.id)
        commentAdapter = CommentAdapter()

        binding.recyclerViewComments.setHasFixedSize(false)
        binding.recyclerViewComments.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewComments.adapter = commentAdapter

        mainViewModel.resComments.observe(this.viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerViewComments.visibility = View.VISIBLE
                    it.data.let { res ->
                        res?.let { it1 -> commentAdapter.setData(it1) }
                    }
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.recyclerViewComments.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerViewComments.visibility = View.VISIBLE
                }
            }
        }
    }
}