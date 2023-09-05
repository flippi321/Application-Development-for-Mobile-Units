package com.example.task1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.util.Log

/**
 * The Main activity of the app
 * Implements the Input fields from below
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InputFields()
        }
    }
}

/**
 * The input fields where the user fills their first and last name
 * Will log the first and last name
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFields() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Hva er fornavnet ditt?") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Hva er etternavnet ditt?") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if(!(firstName.isEmpty() || lastName.isEmpty())){
                Log.w("Leksjon", firstName)
                Log.e("Leksjon", lastName)
                text = "Hei $firstName $lastName!"
            }
        }) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the saved inputs (you can modify this as per your needs)
        Text(text = text)
    }
}
