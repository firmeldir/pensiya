package com.vladhanin.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.vladhanin.myapplication.models.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.enterButton).setOnClickListener {

            findViewById<EditText>(R.id.loginInput).text.toString().let { login ->
                Data.USERS.find { it.login == login }?.let {
                    Data.CURRENT_USER = it
                    startActivity(Intent(this, RetirementDataActivity::class.java))
                } ?: run {
                    Toast.makeText(baseContext, "Повторіть знову", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}