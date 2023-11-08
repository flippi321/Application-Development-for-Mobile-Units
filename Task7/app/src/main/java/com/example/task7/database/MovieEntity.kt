package com.example.task7.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONArray

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val director: String,
    val actors: String // Store the actors list as a JSON string
) {
    fun getActorsList(): List<String> {
        val actorsJsonArray = JSONArray(actors)
        val actorsList = mutableListOf<String>()
        for (i in 0 until actorsJsonArray.length()) {
            actorsList.add(actorsJsonArray.getString(i))
        }
        return actorsList
    }
}

