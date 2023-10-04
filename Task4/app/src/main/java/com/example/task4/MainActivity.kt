package com.example.task4

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSolutions(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add(0, 0, 0,
            "Fasit").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        menu.add(0, 1, 0,
            "Beskrivelse").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        menu.add(0, 2, 0,
            "Vis Fasit").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        menu.add(0, 3, 0,
            "Avslutt").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> Toast.makeText(this, "Fasit valgt",
                Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(this, "Beskrivelse valgt",
                Toast.LENGTH_SHORT).show()
            2 -> showSolutions(true)
            3 -> finish()
            else -> return false
        }
        return true
    }

    private fun showSolutions(show: Boolean) {
        if (show) {
            Toast.makeText(this, "Livet og alts betydning er 42",
                Toast.LENGTH_SHORT).show()
        }
    }
}
