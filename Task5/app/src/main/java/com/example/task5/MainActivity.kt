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

const val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

class MainActivity : AppCompatActivity() {
    private val network: HttpWrapper = HttpWrapper(URL)

    private var correctAnswer = "<X>, du har vunnet 100 kr som kommer inn på ditt kort <Y>"
    private var outOfLives1 = "Tallet <X> er for stort! Beklager ingen flere sjanser, du må starte på nytt (registrer kortnummer og navn)"
    private var outOfLives2 = "Tallet <X> er for lite! Beklager ingen flere sjanser, du må starte på nytt (registrer kortnummer og navn)"
    private val outOfLives3 = "Beklager ingen flere sjanser, du må starte på nytt (registrer kortnummer og navn)"

    private lateinit var nameEditText: EditText
    private lateinit var cardNumberEditText: EditText
    private lateinit var guessEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var tryAgainButton: Button

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        cardNumberEditText = findViewById(R.id.cardNumberEditText)
        guessEditText = findViewById(R.id.guessEditText)
        submitButton = findViewById(R.id.submitButton)
        resultTextView = findViewById(R.id.result)
        tryAgainButton = findViewById(R.id.tryAgainButton)

        submitButton.setOnClickListener {
            if (nameEditText.isEnabled && cardNumberEditText.isEnabled) {
                performRequest(requestParameters())
            } else {
                performRequest(sendNumber())
            }
        }

        tryAgainButton.setOnClickListener {
            resetGame()
        }
    }

    private fun requestParameters(): Map<String, String> {
        val firstName = nameEditText.text.toString()
        val cardNumber = cardNumberEditText.text.toString()

        nameEditText.isEnabled = false
        cardNumberEditText.isEnabled = false
        guessEditText.isEnabled = true
        guessEditText.hint = getString(R.string.enter_your_guess)

        correctAnswer = "$firstName, du har vunnet 100 kr som kommer inn på ditt kort $cardNumber"

        return mapOf(
            "navn" to firstName,
            "kortnummer" to cardNumber,
        )
    }

    private fun sendNumber(): Map<String, String> {
        val number = guessEditText.text.toString()

        outOfLives1 = "Tallet $number er for stort! Beklager ingen flere sjanser, du må starte på nytt (registrer kortnummer og navn)"
        return mapOf(
            "tall" to number
        )
    }

    private fun performRequest(parameterList: Map<String, String>) {
        CoroutineScope(IO).launch {
            val response: String = try {
                network.get(parameterList)
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
        guessEditText.isEnabled = false
        submitButton.isEnabled = true
        tryAgainButton.isEnabled = false

        guessEditText.setText(getString(R.string.enter_information_first))
        resultTextView.text = getString(R.string.result_will_be_shown_here)
    }

    // Method to fix the input from the server which is badly formatted
    private fun cleanResponse(response: String?): String {
        return response?.replace("\nnull", "") ?: ""
    }

    private fun isFinished(response: String) : Boolean{
        return (response.trim() == correctAnswer.trim()
                || response.trim() == outOfLives1.trim()
                || response.trim() == outOfLives2.trim()
                || response.trim() == outOfLives3.trim())
    }

    private fun setResult(response: String?) {
        val fixedResponse = cleanResponse(response)
        resultTextView.text = fixedResponse
        tryAgainButton.isEnabled = true

        // If we are correct or out of lives we only let the user press try again
        if (isFinished(fixedResponse)) {
            guessEditText.isEnabled = false
            submitButton.isEnabled = false
        }
    }
}
