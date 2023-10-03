package com.example.task3

import Venn
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

        // Create an instance of Venn
        btnRegistrer.setOnClickListener {
            val navn = edtNavn.text.toString()
            val dato = edtDato.text.toString()

            venner.add(Venn(navn, dato))

            // Navigate to ListActivity and pass the list
            val intent = Intent(this, ListActivity::class.java)
            intent.putParcelableArrayListExtra("venner", ArrayList(venner))
            startActivity(intent)
        }
    }
}
