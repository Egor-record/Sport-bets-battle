package com.parse

import com.google.gson.Gson





class DataSender {

    val URL_TO_API = "http:localhost:8888/api/v1"

    private fun serialization(games: ArrayList<MutableMap<Any, Any>>): String? {
       return Gson().toJson(games)
    }

    fun sendDataToApi(games : ArrayList<MutableMap<Any, Any>>) {

        val gson = serialization(games)

        println(gson)

    }
}
