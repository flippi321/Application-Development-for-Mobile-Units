package com.example.appserver

import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.PrintWriter
import java.net.NetworkInterface
import java.net.ServerSocket
import java.net.Socket
import java.util.Collections

class AppServer(private val textView: TextView, private val PORT: Int = 12345) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var clientSocket: Socket? = null
    private var clientWriter: PrintWriter? = null

    private var ui: String? = ""
        set(value) {
            coroutineScope.launch(Dispatchers.Main) { textView.text = value }
            field = value
        }

    init {
        ui = null
    }

    fun start() {
        coroutineScope.launch {
            try {
                ui = "Starting Server ..."
                val serverSocket = ServerSocket(PORT)
                ui = "ServerSocket created, waiting for a client to connect..."

                while (true) {
                    val client = serverSocket.accept()
                    handleClient(client)
                }
            } catch (e: Exception) {
                ui = e.message
            }
        }
    }

    private fun handleClient(client: Socket) {
        clientSocket = client
        ui = "Client connected:\n$clientSocket"
        sendToClient("Welcome Client!")
        listenForClientMessages()
    }

    private fun listenForClientMessages() {
        clientSocket?.getInputStream()?.bufferedReader()?.use { reader ->
            try {
                while (true) {
                    val message = reader.readLine() ?: break // Exit if null (disconnected)
                    ui = "Client says:\n$message"
                }
            } catch (e: Exception) {
                ui = "Error reading message from client: ${e.message}"
            }
        }
        cleanupClient()
    }

    private fun sendToClient(message: String) {
        if (clientWriter == null) {
            clientWriter = clientSocket?.getOutputStream()?.let { PrintWriter(it, true) }
        }
        clientWriter?.println(message)
        ui = "Sent to client:\n$message"
    }

    fun sendMessageToClient(message: String) {
        coroutineScope.launch {
            try {
                sendToClient(message)
            } catch (e: Exception) {
                ui = "Error sending message: ${e.message}"
            }
        }
    }

    private fun cleanupClient() {
        clientWriter?.close()
        clientSocket?.close()
        clientWriter = null
        clientSocket = null
        ui = "Client disconnected."
    }
}
