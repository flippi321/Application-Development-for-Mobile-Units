package com.example.task2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : Activity() {
    private val maxNumber = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start Randomizer activity
        val intent = Intent("RandomNumberActivity")

        /* Removed because I don't know wtf this is
        * intent.putExtra("flag", flagValue)
        */

        //startActivityForResult(intent, maxNumber)
    }

    fun onClickGenerateRandomNumber(v: View?){
        Log.w("Warning", "Test")
    }
}