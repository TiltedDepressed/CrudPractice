package com.example.crudapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapplication.R
import com.example.crudapplication.data.model.Post

class UserRecycleAdapter (
    private val list: List<Post>
): RecyclerView.Adapter<UserRecycleAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textViewBody = itemView.findViewById<TextView>(R.id.textViewBody)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTitle.text = list[position].title
        holder.textViewBody.text = list[position].textBody
    }
}