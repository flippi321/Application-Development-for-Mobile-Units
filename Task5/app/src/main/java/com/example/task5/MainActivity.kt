package com.example.task5

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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

    private lateinit var nameEditText: EditText
    private lateinit var cardNumberEditText: EditText
    private lateinit var guessEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        cardNumberEditText = findViewById(R.id.cardNumberEditText)
        guessEditText = findViewById(R.id.guessEditText)
        submitButton = findViewById(R.id.submitButton)
        resultTextView = findViewById(R.id.result)

        submitButton.setOnClickListener {
            if (nameEditText.isEnabled && cardNumberEditText.isEnabled) {
                performRequest(HTTP.GET, requestParameters())
            } else {
                performRequest(HTTP.GET, sendNumber())
            }
        }
    }

    private fun requestParameters(): Map<String, String> {
        val firstName = nameEditText.text.toString()
        val cardNumber = cardNumberEditText.text.toString()

        nameEditText.isEnabled = false
        cardNumberEditText.isEnabled = false

        return mapOf(
            "navn" to firstName,
            "kortnummer" to cardNumber,
        )
    }

    private fun sendNumber(): Map<String, String> {
        val number = guessEditText.text.toString()

        return mapOf(
            "tall" to number
        )
    }

    private fun performRequest(typeOfRequest: HTTP, parameterList: Map<String, String>) {
        CoroutineScope(IO).launch {
            val response: String = try {
                when (typeOfRequest) {
                    HTTP.GET -> network.get(parameterList)
                    HTTP.POST -> TODO()
                    HTTP.GET_WITH_HEADER -> TODO()
                }
            } catch (e: Exception) {
                Log.e("performRequest()", e.message!!)
                e.toString()
            }
            MainScope().launch {
                setResult(response)
            }
        }
    }

    private fun resetGame() {
        nameEditText.isEnabled = true
        cardNumberEditText.isEnabled = true
        guessEditText.isEnabled = true
        submitButton.isEnabled = true
        tryAgainButton.isEnabled = false

        guessEditText.setText("")
        resultTextView.text = "@string/result_will_be_shown_here"
    }

    private fun setResult(response: String?) {
        resultTextView.text = response
    }
}
