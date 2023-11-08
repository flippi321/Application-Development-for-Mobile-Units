package com.example.appserver

import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.PrintWriter
import java.net.InetAddress
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
                val ip = getLocalIpAddress();
                ui = "ServerSocket created on $ip, waiting for a client to connect..."

                while (true) {
                    val client = serverSocket.accept()
                    handleClient(client)
                }
            } catch (e: Exception) {
                ui = e.message
            }
        }
    }

    // Function to get the local IP address
    private fun getLocalIpAddress(): String {
        try {
            val networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in networkInterfaces) {
                val addrs = Collections.list(intf.inetAddresses)
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress) {
                        val sAddr = addr.hostAddress
                        // We prefer IPv4 addresses
                        val isIPv4 = sAddr.indexOf(':') < 0
                        if (isIPv4) {
                            return sAddr
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            // Handle exception
        }
        return "Unavailable"
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
