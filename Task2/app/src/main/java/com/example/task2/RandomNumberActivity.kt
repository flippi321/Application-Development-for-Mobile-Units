package com.example.task2

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class RandomNumberActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)

        val maxValue = intent.getIntExtra("maxValue", 100)
        val generatedNumber = (0..maxValue).random()

        val textView = findViewById<TextView>(R.id.random_number)
        textView.text = "Generert tall: $generatedNumber"
    }
}
