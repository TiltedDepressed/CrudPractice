package com.example.crudapplication.ui.activity


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapplication.R
import com.example.crudapplication.data.datasource.ServiceBuilder
import com.example.crudapplication.data.model.User
import com.example.crudapplication.ui.adapter.MainRecyclerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val apiService = ServiceBuilder.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<User>()
        val adapter = MainRecyclerAdapter(list){
            val intent = Intent(this,UserActivity::class.java)
            intent.putExtra(EXTRA_ID,it)
            startActivity(intent)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
            val users = apiService.getUsers()
            list.addAll(users)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }

    }

    companion object {
      const val EXTRA_ID = "extra_id"
    }
}