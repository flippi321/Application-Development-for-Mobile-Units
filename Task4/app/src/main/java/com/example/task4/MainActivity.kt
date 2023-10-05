package com.example.task4

import Movie
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var currentMovieIndex = 0 // We always start on Batman

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
                // When forrige is pressed we go to the previous movie
                currentMovieIndex--
                if (currentMovieIndex < 0) currentMovieIndex = movies.size - 1  // Handle underflow.
            }
            1 -> {
                // When forrige is pressed we go to the next movie
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
        movies.add(Movie(
            "Batman",
            "The plot follows the vigilante Batman, police lieutenant James Gordon, and district attorney Harvey Dent, who form an alliance to dismantle organized crime in Gotham City. Their efforts are derailed by the Joker, an anarchistic mastermind who seeks to test how far the Batman will go to save the city from chaos.",
            "batman"))
        movies.add(Movie(
            "Prestige",
            "After a tragic accident, two stage magicians in 1890s London engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other.",
            "prestige"))
        movies.add(Movie(
            "Interstellar",
            "Set in a dystopian future where humanity is embroiled in a catastrophic blight and famine, the film follows a group of astronauts who travel through a wormhole near Saturn in search of a new home for humankind.",
            "interstellar"))
        movies.add(Movie(
            "Inception",
            "Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction, stealing valuable secrets from deep within the subconscious during the dream state. Cobb's rare ability has made him a coveted player in the world of corporate espionage, but it has also cost him everything he loves.",
            "inception"))
        movies.add(Movie(
            "Memento",
            "The story of Leonard Shelby, a man with anterograde amnesia, which prevents him from forming new memories. He uses notes and tattoos to hunt for the man he thinks killed his wife.",
            "memento"))
        movies.add(Movie(
            "Dunkirk",
            "The story of the miraculous evacuation of Allied soldiers during WWII from the beaches and harbour of Dunkirk, France, between 26 May and 4 June 1940, after they were cut off and surrounded by the German army.",
            "dunkirk"))
        return movies
    }

    private fun showSolutions(show: Boolean) {
        if (show) {
            Toast.makeText(this, "Livet og alts betydning er 42", Toast.LENGTH_SHORT).show()
        }
    }
}
