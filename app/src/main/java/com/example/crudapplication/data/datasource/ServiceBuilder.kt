package com.example.crudapplication.data.datasource

import com.example.crudapplication.data.interfaces.ApiPostInterface
import com.example.crudapplication.data.interfaces.ApiUserInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
       //коментарий.
        @Volatile
        private var INSTANCE: ApiUserInterface? = null
        fun getService(): ApiUserInterface {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildService()
                }
            }
            return INSTANCE!!
        }
        //инициализация Retrofit
        private fun buildService(): ApiUserInterface {
            val retrofit = Retrofit.Builder() .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiUserInterface::class.java)
        }

        //ApiPostInterface
        private var Post_INSTANCE: ApiPostInterface? = null
        fun getPostService(): ApiPostInterface {
            if (Post_INSTANCE == null) {
                synchronized(this) {
                    Post_INSTANCE = buildPostService()
                }
            }
            return Post_INSTANCE!!
        }
        private fun buildPostService(): ApiPostInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiPostInterface::class.java)
        }


    }
}