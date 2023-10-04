package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListActivity : Activity() {

    private lateinit var venner: MutableList<Venn>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        venner = mutableListOf()
        listView = findViewById(R.id.listVenner)

        val adapter = VennAdapter(this, venner)
        listView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabAddVenn).setOnClickListener {
            val intent = Intent(this@ListActivity, RegisterActivity::class.java)
            startActivityForResult(intent, 69)  // Reality can be whatever I want
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedVenn = venner[position]
            val intent = Intent(this@ListActivity, RegisterActivity::class.java)
            intent.putExtra("selectedVenn", selectedVenn)  // Pass the selected Venn to RegisterActivity
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val newVenn = data?.getParcelableExtra<Venn>("newVenn")
            if (newVenn != null) {
                venner.add(newVenn)
                (listView.adapter as VennAdapter).notifyDataSetChanged()
            }
        }
    }
}
