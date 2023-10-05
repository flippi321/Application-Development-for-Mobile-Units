package com.example.task4

import Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.ListView

class MovieListFragment : Fragment() {

    companion object {
        private const val MOVIE_LIST_KEY = "movieList"

        fun newInstance(movies: ArrayList<Movie>): MovieListFragment {
            val fragment = MovieListFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(MOVIE_LIST_KEY, movies)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val listView: ListView = view.findViewById(R.id.movieList)

        val movies: ArrayList<Movie>? = arguments?.getParcelableArrayList(MOVIE_LIST_KEY)
        val movieNames = movies?.map { it.name }?.toTypedArray() ?: arrayOf()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, movieNames)
        listView.adapter = adapter

        return view
    }
}
