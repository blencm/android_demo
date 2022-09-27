package com.example.app.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.CommentItemBinding
import com.example.app.databinding.FragmentItemBinding
import com.example.app.models.Post
import com.example.app.models.PostComment
import com.example.app.utilities.BaseRecyclerClickListener
import dagger.hilt.android.plugin.util.capitalize
import javax.inject.Inject

/**
 * Created by Bleker Cordova
 */

class CommentAdapter @Inject constructor(): RecyclerView.Adapter<CommentAdapter.ViewHolder>(){

    private var items = ArrayList<PostComment>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<PostComment>) {
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PostComment) =
            with(binding) {
                name.text = data.name!!.capitalize()
                comment.text = data.body!!.capitalize()
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])
}