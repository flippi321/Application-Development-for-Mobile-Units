package com.example.task4

import Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView

class MovieDescriptionFragment : Fragment() {

    companion object {
        private const val MOVIE_DESCRIPTION_KEY = "movieDescription"

        fun newInstance(movie: Movie): MovieDescriptionFragment {
            val fragment = MovieDescriptionFragment()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_DESCRIPTION_KEY, movie)
            fragment.arguments = bundle
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val description: TextView = view.findViewById(R.id.description)

        val movie: Movie? = arguments?.getParcelable(MOVIE_DESCRIPTION_KEY)

        // TODO use image from Movie object instead of batman
        imageView.setImageResource(R.drawable.batman)

        description.text = movie?.description ?: "Description not available"

        return view
    }

}
