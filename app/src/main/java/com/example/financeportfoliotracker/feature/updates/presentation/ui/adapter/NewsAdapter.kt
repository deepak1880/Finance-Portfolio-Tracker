package com.example.financeportfoliotracker.feature.updates.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financeportfoliotracker.databinding.ItemPostBinding
import com.example.financeportfoliotracker.feature.updates.domain.model.News

class NewsAdapter(private var postList: List<News>) :
    RecyclerView.Adapter<NewsAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.binding.tvTitle.text = post.title
        holder.binding.tvBody.text = post.body
    }

    override fun getItemCount(): Int = postList.size

    fun updateData(newPosts: List<News>) {
        postList = newPosts
        notifyDataSetChanged()
    }
}
