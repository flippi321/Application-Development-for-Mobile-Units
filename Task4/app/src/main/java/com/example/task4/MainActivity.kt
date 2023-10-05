package com.example.task4

import Movie
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var currentMovieIndex = 0 // Always start with the first movie in the list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "IMbD"
        showSolutions(false)

        val movies = createMovies()

        val movieListFragment = MovieListFragment.newInstance(movies)
        val movieDescriptionFragment = MovieDescriptionFragment.newInstance(movies[currentMovieIndex])

        supportFragmentManager.beginTransaction()
            .replace(R.id.imageFragmentContainer, movieDescriptionFragment)
            .replace(R.id.movieListFragmentContainer, movieListFragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, 0, 0, "Forrige")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menu.add(0, 1, 1, "Neste")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val movies = createMovies()  // Retrieve the movie list.

        when (item.itemId) {
            0 -> {
                // Forrige clicked, decrease the index
                currentMovieIndex--
                if (currentMovieIndex < 0) currentMovieIndex = movies.size - 1  // Handle underflow.
            }
            1 -> {
                // Neste clicked, increase the index
                currentMovieIndex++
                if (currentMovieIndex >= movies.size) currentMovieIndex = 0  // Handle overflow.
            }
            else -> return super.onOptionsItemSelected(item)
        }

        // Replace the movieDescriptionFragment with the new movie based on currentMovieIndex.
        val newMovieDescriptionFragment = MovieDescriptionFragment.newInstance(movies[currentMovieIndex])
        supportFragmentManager.beginTransaction()
            .replace(R.id.imageFragmentContainer, newMovieDescriptionFragment)
            .commit()

        return true
    }


    private fun createMovies() : ArrayList<Movie>{
        val movies = ArrayList<Movie>()
        movies.add(Movie("Batman", "I am vengance", "batman"))
        movies.add(Movie("Prestige", "I am actually watching Prestige this weekend", "prestige"))
        movies.add(Movie("Interstellar", "Space is cool", "interstellar"))
        return movies
    }

    private fun showSolutions(show: Boolean) {
        if (show) {
            Toast.makeText(this, "Livet og alts betydning er 42", Toast.LENGTH_SHORT).show()
        }
    }
}
