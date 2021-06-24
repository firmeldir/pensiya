package com.vladhanin.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vladhanin.myapplication.models.User

class IncoreActivity : AppCompatActivity() {

    companion object {

        var user: User? = null

        fun start(activity: AdminEditorActivity, user: User) {
            this.user = user
            activity.startActivity(Intent(activity, IncoreActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incore)
        val rv = findViewById<RecyclerView>(R.id.income)
        val pv = findViewById<TextView>(R.id.pension_size)
        val adapter = IncomeAdapter()
        rv.adapter = adapter
        val u = if (user == null) Data.CURRENT_USER else user

        u?.incomes?.let {
            val sum = it.map { income -> income.money.toIntOrNull() ?: 0 }.sum()
            pv.text = (0.85 * sum / it.count()).toInt().toString()
            adapter.add(it)
        }
    }
}