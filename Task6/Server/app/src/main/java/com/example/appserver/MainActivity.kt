package com.example.appserver

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var server: AppServer
    private lateinit var buttonSend: Button
    private lateinit var editTextInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        editTextInput = findViewById(R.id.editTextInput)
        buttonSend = findViewById(R.id.buttonSend)

        // Initialize the server
        server = AppServer(textView)

        // Set up the button event listener
        buttonSend.setOnClickListener {
            val message = editTextInput.text.toString()
            if (message.isNotBlank()) {
                server.sendMessageToClient(message)
                editTextInput.text.clear()
            }
        }

        // Start the server
        server.start()
    }
}
