package com.example.task2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.task2.ui.theme.Task2Theme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val value = intent.getIntExtra("random_number", 100)
        val textView = TextView(this)
        textView.text = "Tilfeldig nummer: $value"

        setContentView(textView)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun RandomNumber(context: Context, maxSize: Int) {
    val value = (0..maxSize).random()
    val intent = Intent(context, MainActivity::class.java)
    intent.putExtra("random_number", value)
    Toast.makeText(context, "Intent returned: $value", Toast.LENGTH_LONG).show()
    context.startActivity(intent)
}
