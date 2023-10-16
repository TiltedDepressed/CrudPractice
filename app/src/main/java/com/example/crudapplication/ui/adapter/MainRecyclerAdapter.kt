package com.example.crudapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapplication.R
import com.example.crudapplication.data.model.User

class MainRecyclerAdapter(

    private val list: List<User>,
    private val onClick: (id:Int) -> Unit

    ) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)
        val emailTextView : TextView = itemView.findViewById(R.id.emailTextView)
        init{
            itemView.setOnClickListener{
                onClick(list[adapterPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.nameTextView.text = list[position].name
        holder.emailTextView.text = list[position].email
    }

}