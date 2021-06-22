package com.vladhanin.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import com.google.android.material.button.MaterialButton

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        findViewById<MaterialButton>(R.id.acceptButton).setOnClickListener {
            getSharedPreferences(C_SP, Context.MODE_PRIVATE).edit {
                putBoolean(C_SHOW_REQUEST, false)
            }
            Toast.makeText(baseContext, "Підтверджено", Toast.LENGTH_LONG).show()
            finish()
        }

        findViewById<MaterialButton>(R.id.rejectButton).setOnClickListener {
            getSharedPreferences(C_SP, Context.MODE_PRIVATE).edit {
                putBoolean(C_SHOW_REQUEST, false)
            }
            Toast.makeText(baseContext, "Відхилено", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}