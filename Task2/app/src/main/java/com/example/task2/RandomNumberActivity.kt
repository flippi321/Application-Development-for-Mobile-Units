package com.example.task2

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class RandomNumberActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)

        val maxValue = intent.getIntExtra("maxValue", 1)
        val generatedNumber = (0..maxValue).random()

        Toast.makeText(this, "Tilfeldig tall: $generatedNumber", Toast.LENGTH_LONG).show()

        val textView = findViewById<TextView>(R.id.random_number)
        val result = "Generert tall: $generatedNumber"
        textView.text = result
    }
}
