package com.example.banktest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.banktest.R
import com.example.banktest.model.Post

class PostAdapter: RecyclerView.Adapter<PostAdapter.CustomViewHolder>() {

    private var outputList: MutableList<Post> = mutableListOf()

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val userId: TextView = view.findViewById(R.id.userid_text)
        val id: TextView = view.findViewById(R.id.id_text)
        val title: TextView = view.findViewById(R.id.title_text)
        val body: TextView = view.findViewById(R.id.body_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return outputList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.userId.text = outputList[position].userId.toString()
        holder.id.text = outputList[position].id.toString()
        holder.title.text = outputList[position].title
        holder.body.text = outputList[position].body
    }

    fun update(posts: MutableList<Post>) {
        this.outputList.clear()
        this.outputList = posts
        notifyDataSetChanged()
    }
}