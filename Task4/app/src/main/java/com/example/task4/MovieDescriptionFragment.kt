package com.example.task4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView

class MovieDescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val description: TextView = view.findViewById(R.id.description)

        // TODO FIX
        imageView.setImageResource(R.drawable.batman)
        description.text = getString(R.string.some_description)

        return view
    }
}
