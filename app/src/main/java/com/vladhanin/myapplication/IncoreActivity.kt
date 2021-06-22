package com.vladhanin.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class IncoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incore)
        val rv = findViewById<RecyclerView>(R.id.income)
        val adapter = IncomeAdapter()
        rv.adapter = adapter
        Data.CURRENT_USER?.incomes?.let { adapter.add(it) }
    }
}