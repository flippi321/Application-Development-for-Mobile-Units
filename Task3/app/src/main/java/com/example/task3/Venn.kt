package com.example.task3
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Venn(val navn: String, var fødselsdato: String) : Parcelable
