package com.example.task4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.task4.ui.theme.Task4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        MyAppBar()
                        Greeting("Android")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar() {
    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current  // Capture the context outside the lambdas

    TopAppBar(
        title = { Text("My App") },
        actions = {
            IconButton(onClick = { /* Handle Fasit action here */ }) {
                Text("Fasit")
            }
            IconButton(onClick = { showMenu = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                Text("Beskrivelse", modifier = Modifier.clickable {
                    Toast.makeText(context, "Ganske selvbeskrivende", Toast.LENGTH_SHORT).show()
                    showMenu = false
                })
                Text("Innstillinger", modifier = Modifier.clickable {
                    Toast.makeText(context, "Du har nå akseptert alle Cookies :P", Toast.LENGTH_SHORT).show()
                    showMenu = false
                })
                Text("Avslutt", modifier = Modifier.clickable {
                    Toast.makeText(context, "App will close", Toast.LENGTH_SHORT).show()
                    showMenu = false
                    // Implement exit function here
                })
            }
        }
    )
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Task4Theme {
        Greeting("Android")
    }
}
