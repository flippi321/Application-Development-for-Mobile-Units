package com.example.task7

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException

class MainActivity : Activity() {
    private lateinit var moviesList: MutableList<Movie>
    private lateinit var adapter: ArrayAdapter<Movie>
    private lateinit var listView: ListView
    private lateinit var mainLayout: View // Add this line to hold the main layout reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val btnSort = findViewById<Button>(R.id.btnSort)
        val btnBackground = findViewById<Button>(R.id.btnBackground)
        mainLayout = findViewById<View>(R.id.mainLayout)

        btnSort.setOnClickListener {
            showSortMenu(btnSort)
        }

        btnBackground.setOnClickListener {
            showBackgroundColorMenu(btnBackground)
        }

        // Read and parse the movies from the JSON file
        val moviesList = readMoviesFromAssets()

        // After reading the movies, write them to the internal storage as JSON
        writeMoviesToJsonFile(moviesList)

        // Adapter to display the movies in the ListView
        this.moviesList = readMoviesFromAssets().toMutableList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, moviesList)
        listView.adapter = adapter
    }

    private fun showSortMenu(anchor: View) {
        val popup = PopupMenu(this, anchor)
        popup.menuInflater.inflate(R.menu.menu_main, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            onOptionsItemSelected(item)
        }
        popup.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_none -> {
                sortMoviesByCondition { true }
                true
            }
            R.id.sort_keanu_reeves -> {
                sortMoviesByCondition { movie -> movie.hasActor("Keanu Reeves") }
                true
            }
            R.id.sort_christopher_nolan -> {
                sortMoviesByCondition { movie -> movie.isDirectedBy("Christopher Nolan") }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showBackgroundColorMenu(anchor: View) {
        val popup = PopupMenu(this, anchor)
        popup.menu.add(Menu.NONE, 1, Menu.NONE, "White")
        popup.menu.add(Menu.NONE, 2, Menu.NONE, "Yellow")
        popup.menu.add(Menu.NONE, 3, Menu.NONE, "Red")
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                1 -> mainLayout.setBackgroundColor(Color.WHITE)
                2 -> mainLayout.setBackgroundColor(Color.YELLOW)
                3 -> mainLayout.setBackgroundColor(Color.RED)
            }
            true
        }
        popup.show()
    }

    private fun sortMoviesByCondition(condition: (Movie) -> Boolean) {
        val sortedList = if (condition == { true }) {
            moviesList
        } else {
            moviesList.filter(condition)
        }
        adapter.clear()
        adapter.addAll(sortedList)
        adapter.notifyDataSetChanged()
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

    private fun writeMoviesToJsonFile(movies: List<Movie>) {
        val jsonArray = JSONArray()
        movies.forEach { movie ->
            jsonArray.put(movie.toJson())
        }

        val jsonString = jsonArray.toString()

        try {
            val fileOutputStream = openFileOutput("new_movies.json", MODE_PRIVATE)
            fileOutputStream.write(jsonString.toByteArray())
            fileOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}