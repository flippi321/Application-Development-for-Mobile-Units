package com.example.task4

import Movie
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "IMbD"
        showSolutions(false)

        val movies = createMovies()

        val movieListFragment = MovieListFragment.newInstance(movies)

        supportFragmentManager.beginTransaction()
            .replace(R.id.imageFragmentContainer, ImageFragment())
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
        when (item.itemId) {
            0 -> Toast.makeText(this, "Forrige clicked", Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(this, "Neste clicked", Toast.LENGTH_SHORT).show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun createMovies() : ArrayList<Movie>{
        val movies = ArrayList<Movie>()
        movies.add(Movie("Batman", "I am vengance", ""))
        movies.add(Movie("Prestige", "I am actually watching Prestige this weekend", ""))
        movies.add(Movie("Interstellar", "Space is cool", ""))
        return movies
    }

    private fun showSolutions(show: Boolean) {
        if (show) {
            Toast.makeText(this, "Livet og alts betydning er 42", Toast.LENGTH_SHORT).show()
        }
    }
}
