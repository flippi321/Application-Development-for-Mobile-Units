package com.example.task7

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        // Sample list of movies
        val moviesList = listOf(
            Movie("Inception", "Christopher Nolan", listOf("Leonardo DiCaprio", "Joseph Gordon-Levitt")),
            Movie("The Matrix", "Lana Wachowski, Lilly Wachowski", listOf("Keanu Reeves", "Laurence Fishburne")),
            // Add more movies here
        )

        // Adapter to display the movies in the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, moviesList)
        listView.adapter = adapter
    }
}
