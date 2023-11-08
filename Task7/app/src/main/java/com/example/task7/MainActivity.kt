package com.example.task7

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        // Read and parse the movies from the JSON file
        val moviesList = readMoviesFromAssets()

        // Adapter to display the movies in the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, moviesList)
        listView.adapter = adapter
    }

    private fun readMoviesFromAssets(): List<Movie> {
        val inputStream = resources.openRawResource(R.raw.movies)
        val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)

        val movies = mutableListOf<Movie>()
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val name = jsonObject.getString("name")
            val director = jsonObject.getString("director")
            val actorsJsonArray = jsonObject.getJSONArray("actors")
            val actorsList = mutableListOf<String>()
            for (j in 0 until actorsJsonArray.length()) {
                actorsList.add(actorsJsonArray.getString(j))
            }
            movies.add(Movie(name, director, actorsList))
        }

        return movies
    }
}