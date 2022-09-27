package com.example.app.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.FragmentItemBinding
import com.example.app.models.Post
import com.example.app.utilities.BaseRecyclerClickListener
import dagger.hilt.android.plugin.util.capitalize
import javax.inject.Inject

/**
 * Created by Bleker Cordova
 */

class PostAdapter @Inject constructor(private var clickListener: BaseRecyclerClickListener<Post>
): RecyclerView.Adapter<PostAdapter.ViewHolder>(){

    private var items = ArrayList<Post>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<Post>) {
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Post, clickListener: BaseRecyclerClickListener<Post>?) =
            with(binding) {
                title.text = data.title!!.capitalize()
                content.text = data.body!!.capitalize()
                root.setOnClickListener {
                    clickListener?.onClick(data, bindingAdapterPosition)
                }
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], clickListener)
}