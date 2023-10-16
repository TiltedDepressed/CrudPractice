package com.example.crudapplication.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudapplication.R
import com.example.crudapplication.data.datasource.ServiceBuilder
import com.example.crudapplication.data.model.Post
import com.example.crudapplication.ui.adapter.UserRecycleAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserActivity : AppCompatActivity() {
    private val apiService = ServiceBuilder.getService()
    private val apiPostService = ServiceBuilder.getPostService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val name = findViewById<TextView>(R.id.textViewName)
        val email = findViewById<TextView>(R.id.textViewEmail)
        val username = findViewById<TextView>(R.id.textViewUsername)
        val website = findViewById<TextView>(R.id.textViewWebsite)
        val phone = findViewById<TextView>(R.id.textViewPhone)

        val id : Int = intent.getIntExtra(MainActivity.EXTRA_ID, 0)

        lifecycleScope.launch(Dispatchers.IO) {
            val user = apiService.getUserById(id)
            withContext(Dispatchers.Main) {
                name.text = user?.name
                username.text = user?.username
                email.text = user?.email
                website.text = user?.website
                phone.text = user?.phone
            }
        }

        //формирование списка статей
        val list = mutableListOf<Post>()
        val adapter = UserRecycleAdapter(list)
        val postRecycler =
            findViewById<RecyclerView>(R.id.postRecyclerView)
        postRecycler.layoutManager =
            LinearLayoutManager(this)
        postRecycler.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {
            val posts = apiPostService.getPostByUserId(id)
            list.addAll(posts)
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }

        val buttonAdd = findViewById<Button>(R.id.addPostButton)
        buttonAdd.setOnClickListener{
            val intent = Intent(this,AddPostActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }


    }
}