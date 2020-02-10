package com.parse

import com.google.gson.Gson
import okhttp3.*


class DataSender {

    val url_to_api = "http://localhost:8888/bets-demo/"

    private fun serialization(games: ArrayList<MutableMap<Any, Any>>): String? {
       return Gson().toJson(games)
    }

    fun sendDataToApi(games : ArrayList<MutableMap<Any, Any>>) {

        val gson = serialization(games)

        println(gson)

        Thread(Runnable {

            val client: OkHttpClient = OkHttpClient().newBuilder()
                .build()
            val mediaType: MediaType? = MediaType.parse("application/json")
            val body =
                RequestBody.create(mediaType, gson.toString())
            val request: Request = Request.Builder()
                .url(url_to_api)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build()
            val response: Response = client.newCall(request).execute()

            println(response)

        }).start()


    }
}
