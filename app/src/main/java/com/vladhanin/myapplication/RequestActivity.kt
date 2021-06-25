package com.vladhanin.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.vladhanin.myapplication.models.Job
import com.vladhanin.myapplication.models.User

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        findViewById<MaterialButton>(R.id.acceptButton).setOnClickListener {
            getSharedPreferences(C_SP, Context.MODE_PRIVATE).edit {
                putInt(C_SHOW_REQUEST, 3)
            }
            Toast.makeText(baseContext, "Підтверджено", Toast.LENGTH_LONG).show()
            finish()
        }

        findViewById<MaterialButton>(R.id.rejectButton).setOnClickListener {
            getSharedPreferences(C_SP, Context.MODE_PRIVATE).edit {
                putInt(C_SHOW_REQUEST, 3)
            }
            Toast.makeText(baseContext, "Відхилено", Toast.LENGTH_LONG).show()
            finish()
        }

        findViewById<RecyclerView>(R.id.jobs).adapter = JobAdapter(Data.TEST_USER.jobs)
        findViewById<RecyclerView>(R.id.incomes).adapter = IncomeAdapter().apply {
            add(Data.TEST_USER.incomes)
        }
    }
}