package com.example.task5

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONArray

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

class MainActivity : AppCompatActivity() {
    private val network: HttpWrapper = HttpWrapper(URL)
    //private val network: HttpWrapper = HttpWrapper(URL_JSON)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add("Try new").setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        menu.add("Guess").setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        menu.add("GET (header)").setShowAsAction(SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            "Try new" -> performRequest(HTTP.GET, requestParameters())
            "Guess" -> performRequest(HTTP.GET, sendNumber())
            "GET (header)" -> performRequest(HTTP.GET_WITH_HEADER,
                requestParameters())
            else -> return false
        }
        return true
    }
    private fun requestParameters(): Map<String, String> {
        val firstName = "Test"
        val cardNumber = "123"

        return mapOf(
            "navn" to firstName,
            "kortnummer" to cardNumber,
        )
    }
    private fun sendNumber(): Map<String, String> {
        val number = "5"

        return mapOf(
            "tall" to number
        )
    }
    private fun performRequest(typeOfRequest: HTTP, parameterList:
    Map<String, String>) {
        CoroutineScope(IO).launch {
            val response: String = try {
                when (typeOfRequest) {
                    HTTP.GET -> network.get(parameterList)
                    HTTP.POST -> network.post(parameterList)
                    HTTP.GET_WITH_HEADER ->
                        network.getWithHeader(parameterList)
                }
            } catch (e: Exception) {
                Log.e("performRequest()", e.message!!)
                e.toString()
            }
            // Endre UI paa hovedtraaden
            MainScope().launch {
                setResult(response)
            //setResult(formatJsonString(response))
            }
        }
    }
    private fun setResult(response: String?) {
        findViewById<TextView>(R.id.result).text = response
    }
    private fun formatJsonString(str: String): String {
        return try {
            JSONArray(str).toString(4)
        } catch (e: Exception) {
            Log.e("formatJsonString()", e.toString())
            e.message!!
        }
    }
}