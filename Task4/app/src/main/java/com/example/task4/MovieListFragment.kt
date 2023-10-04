package com.example.task4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.ListView

class MovieListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val listView: ListView = view.findViewById(R.id.movieList)
        val movies = resources.getStringArray(R.array.movies)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, movies)
        listView.adapter = adapter

        return view
    }
}
