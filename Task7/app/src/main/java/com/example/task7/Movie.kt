package com.example.task7

import org.json.JSONArray
import org.json.JSONObject

data class Movie(
    val name: String,
    val director: String,
    val actors: List<String>
) {
    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("director", director)
        val actorsArray = JSONArray()
        actors.forEach { actor ->
            actorsArray.put(actor)
        }
        jsonObject.put("actors", actorsArray)
        return jsonObject
    }

    override fun toString(): String {
        return "Movie(name='$name', director='$director', actors=${actors.joinToString(", ")})"
    }
}
