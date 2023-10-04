package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private val venner = mutableListOf<Venn>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrering)

        val btnRegistrer = findViewById<Button>(R.id.btnRegistrer)
        val edtNavn = findViewById<EditText>(R.id.edtNavn)
        val edtDato = findViewById<EditText>(R.id.edtDato)

        // Check if there's a Venn passed in the intent
        val selectedVenn = intent.getParcelableExtra<Venn>("selectedVenn")
        if (selectedVenn != null) {
            edtNavn.setText(selectedVenn.navn)
            edtDato.setText(selectedVenn.fødselsdato)
        }

        btnRegistrer.setOnClickListener {
            val navn = edtNavn.text.toString()
            val dato = edtDato.text.toString()

            venner.add(Venn(navn, dato))

            // Set the result for ListActivity and finish this activity
            val resultIntent = Intent()
            resultIntent.putExtra("newVenn", Venn(navn, dato))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
