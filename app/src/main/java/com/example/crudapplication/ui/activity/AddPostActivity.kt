package com.example.crudapplication.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.crudapplication.R
import com.example.crudapplication.data.datasource.ServiceBuilder
import com.example.crudapplication.data.model.Post
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPostActivity : AppCompatActivity() {
    private val apiService = ServiceBuilder.getPostService()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        val list = mutableListOf<Post>()
       val button : Button = findViewById(R.id.addButton)
        button.setOnClickListener{
            createPost()
        }
    }

    private fun createPost() {
        val titlePost : EditText = findViewById(R.id.titleEditText)
        val bodyPost: EditText = findViewById(R.id.bodyEditText)
        val id : Int = intent.getIntExtra("id",0)
        lifecycleScope.launch(Dispatchers.IO) {
            val body = JsonObject().apply {
                addProperty("userId",id.toString())
                addProperty("textBody", bodyPost.text.toString())
                addProperty("title",titlePost.text.toString())
            }
            val result = apiService.createPost(body)
            if(result.isSuccessful){
                Log.e("0000","Статья создана ${result.body()}")
            } else{
                Log.e("0000","Статья не создана ${result.body()}")
            }
        }
    }
}