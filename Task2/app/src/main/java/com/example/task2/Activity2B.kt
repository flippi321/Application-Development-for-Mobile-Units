package com.example.task2

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class Activity2B : Activity() {
    private var number1: Int = 3
    private var number2: Int = 5
    private var upperLimit: Int = 10

    private var number1TextView: TextView? = null
    private var number2TextView: TextView? = null
    private var answerView: EditText? = null
    private var upperLimitView: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2b)

        // Get necessary views
        number1TextView = findViewById<View>(R.id.display_number1) as TextView
        number2TextView = findViewById<View>(R.id.display_number2) as TextView
        answerView = findViewById<View>(R.id.answer) as EditText
        upperLimitView = findViewById<View>(R.id.upperlimit) as EditText

        // Update view values
        number1TextView?.text = "${getString(R.string.number)} 1: $number1"
        number2TextView?.text = "${getString(R.string.number)} 2: $number2"
        upperLimitView?.setText("$upperLimit")
    }

    /**
     * On add numbers button click
     */
    fun addOnClick(v: View?) {
        val correctAnswer = number1 + number2
        val answer = Integer.parseInt(answerView?.text.toString())
        showResult(
            if (correctAnswer == answer) "${getString(R.string.correct)} $number1 + $number2 = $correctAnswer" else "${
                getString(
                    R.string.wrong_the_correct_answer_is
                )
            }: $number1 * $number2 = $correctAnswer"
        )
    }

    /**
     * On multiply numbers button click
     */
    fun multiplyOnClick(v: View?) {
        val correctAnswer = number1 * number2
        val answer = Integer.parseInt(answerView?.text.toString())
        showResult(
            if (correctAnswer == answer) "${getString(R.string.correct)} $number1 * $number2 = $correctAnswer" else "${
                getString(
                    R.string.wrong_the_correct_answer_is
                )
            }: $number1 * $number2 = $correctAnswer"
        )
    }

    /**
     * Show result in alert dialog and offer to generate new random numbers or try again
     */
    private fun showResult(message: String) {
        AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
            .setTitle("Exercise2, task 2")
            .setMessage(message)
            .setPositiveButton(getString(R.string.generate_random_number)) { _, _ ->
                upperLimit = Integer.parseInt(upperLimitView?.text.toString())
                val intent = Intent(this, RandomNumberActivity::class.java)
                intent.putExtra("upperLimit", upperLimit)
                startActivityForResult(intent, 1001) // Use a request code, e.g., 1001
            }
            .setNegativeButton(getString(R.string.try_again), null)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            val generatedNumber1 = data?.getIntExtra("randomNumber1", number1) ?: number1
            val generatedNumber2 = data?.getIntExtra("randomNumber2", number2) ?: number2
            number1 = generatedNumber1
            number2 = generatedNumber2
            number1TextView?.text = "${getString(R.string.number)} 1: $number1"
            number2TextView?.text = "${getString(R.string.number)} 2: $number2"
        }
    }
}
