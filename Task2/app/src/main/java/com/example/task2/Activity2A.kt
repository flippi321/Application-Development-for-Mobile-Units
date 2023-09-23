package com.example.task2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class Activity2A : Activity() {
    private val maxNumber = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2a)
    }

    fun onClickGenerateRandomNumber(v: View?) {
        val intent = Intent(this, RandomNumberActivity::class.java)
        intent.putExtra("maxValue", maxNumber)
        startActivity(intent)
    }
}
