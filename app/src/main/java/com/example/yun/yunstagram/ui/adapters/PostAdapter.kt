package com.example.yun.yunstagram.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yun.yunstagram.data.Post
import com.example.yun.yunstagram.databinding.ListItemPostBinding
import com.example.yun.yunstagram.ui.post.PostItemUserActionsListener
import com.example.yun.yunstagram.ui.profile.ProfileViewModel

class PostAdapter(private val viewModel: ProfileViewModel) : ListAdapter<Post, PostAdapter.ViewHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.apply {
            bind(createOnClickListener(post.id), post)
            itemView.tag = post
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false), viewModel)
    }

    private fun createOnClickListener(postId: String?): View.OnClickListener {
        return View.OnClickListener {

        }
    }

    class ViewHolder(
        private val binding: ListItemPostBinding,
        private val viewModel: ProfileViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Post) {
            val userActionsListener = object : PostItemUserActionsListener {
                override fun onPostClicked(post: Post) {
                    viewModel.openPost(post.id)
                }
            }

            binding.apply {
                clickListener = listener
                actionsListener = userActionsListener
                post = item
                executePendingBindings()
            }
        }
    }
}

private class PostDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}