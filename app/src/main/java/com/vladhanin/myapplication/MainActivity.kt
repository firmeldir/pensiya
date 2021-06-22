package com.vladhanin.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.enterButton).setOnClickListener {
            when(findViewById<EditText>(R.id.loginInput).text.toString()){
                "firmeldir" -> {
                    startActivity(
                        Intent(this, RetirementDataActivity::class.java).apply {
                            putExtra(C_ACCOUNT_NUMBER, 1)
                        }
                    )
                }
                "admin" -> {
                    startActivity(
                        Intent(this, RetirementDataActivity::class.java).apply {
                            putExtra(C_ACCOUNT_NUMBER, 2)
                        }
                    )
                }
                else -> {
                    Toast.makeText(baseContext, "Повторіть знову", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}