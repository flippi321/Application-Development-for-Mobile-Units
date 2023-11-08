package com.example.task7

data class Movie(
    val name: String,
    val director: String,
    val actors: List<String>
) {
    override fun toString(): String {
        return "Movie(name='$name', director='$director', actors=${actors.joinToString(", ")})"
    }
}