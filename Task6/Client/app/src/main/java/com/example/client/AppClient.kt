package com.example.client

import android.widget.TextView
import kotlinx.coroutines.*
import java.io.PrintWriter
import java.net.Socket

class AppClient(private val textView: TextView, private val serverIp: String = "10.0.2.16", private val PORT: Int = 12345) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var serverSocket: Socket? = null
    private var outputWriter: PrintWriter? = null

    private var ui: String? = ""
        set(value) {
            coroutineScope.launch(Dispatchers.Main) { textView.text = value }
            field = value
        }

    fun start() {
        coroutineScope.launch {
            try {
                ui = "Connecting to Server..."
                serverSocket = Socket(serverIp, PORT)
                outputWriter = PrintWriter(serverSocket!!.getOutputStream(), true)
                ui = "Connected to Server"

                listenForServerMessages()
            } catch (e: Exception) {
                ui = e.message
            }
        }
    }

    fun sendMessageToServer(message: String) {
        coroutineScope.launch {
            try {
                outputWriter?.println(message)
                ui = "Sent to server: $message"
            } catch (e: Exception) {
                ui = "Error sending message: ${e.message}"
            }
        }
    }

    private fun listenForServerMessages() {
        serverSocket?.getInputStream()?.bufferedReader()?.use { reader ->
            try {
                while (true) {
                    val message = reader.readLine() ?: break // Exit if null (disconnected)
                    ui = "Server says:\n$message"
                }
            } catch (e: Exception) {
                ui = "Error reading message from server: ${e.message}"
            }
        }
        cleanup()
    }

    private fun cleanup() {
        coroutineScope.launch {
            try {
                outputWriter?.close()
                serverSocket?.close()
                ui = "Disconnected from Server"
            } catch (e: Exception) {
                ui = "Error cleaning up: ${e.message}"
            }
        }
    }
}
