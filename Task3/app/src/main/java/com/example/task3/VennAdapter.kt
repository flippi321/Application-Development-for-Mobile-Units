package com.example.task3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

// Adapter for Venn klasse
class VennAdapter(private val context: Context, private val venner: List<Venn>) : BaseAdapter() {

    override fun getCount(): Int = venner.size

    override fun getItem(position: Int): Any = venner[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val venn = getItem(position) as Venn
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_item_venn, parent, false)

        val txtNavn = rowView.findViewById<TextView>(R.id.txtNavn)
        val txtDato = rowView.findViewById<TextView>(R.id.txtDato)
        txtNavn.text = venn.navn
        txtDato.text = venn.fødselsdato

        return rowView
    }
}
