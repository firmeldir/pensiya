package com.vladhanin.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IncoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incore)
        val rv = findViewById<RecyclerView>(R.id.income)
        val pv = findViewById<TextView>(R.id.pension_size)
        val adapter = IncomeAdapter()
        rv.adapter = adapter
        Data.CURRENT_USER?.incomes?.let {
            val sum =  it.map { income -> income.money.toIntOrNull() ?: 0 }.sum()
            pv.text = (0.85 * sum / it.count()).toInt().toString()
            adapter.add(it)
        }
    }
}