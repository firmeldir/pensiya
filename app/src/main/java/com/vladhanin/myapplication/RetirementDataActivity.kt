package com.vladhanin.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class RetirementDataActivity : AppCompatActivity() {

    private enum class Role {
        ADMIN, USER, VIEWER
    }

    companion object{
        private val ROLE = Role.USER
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retirement_data)

        val number = intent.extras?.getInt(C_ACCOUNT_NUMBER)
        val cname: String? = intent.extras?.getString(C_NAME)
        val csurname: String? = intent.extras?.getString(C_SURNAME)

        var name = ""
        var surname = ""

        val role = when(number){
            1 -> {
                name = "Дмитро"
                surname = "Ковальчуков"
                Role.USER
            }
            2 -> {
                name = "Данжен"
                surname = "Местерович"
                Role.ADMIN
            }
            else -> {
                name = cname ?: ""
                surname = csurname ?: ""
                Role.VIEWER
            }
        }

        when(role){
            Role.ADMIN -> {
                findViewById<MaterialButton>(R.id.searchForRetirementDataButton).apply {
                    isGone = false
                    setOnClickListener{ navigateToSearch() }
                }
                findViewById<MaterialButton>(R.id.allUsersDataButton).apply {
                    isGone = false
                    setOnClickListener{ navigateToSearch() }
                }
                findViewById<MaterialTextView>(R.id.adminModeText).isGone = false
            }
            Role.USER -> {
                with(findViewById<MaterialButton>(R.id.requestForPensionButton)){
                    isGone = false
                    setOnClickListener {
                        getSharedPreferences(C_SP, Context.MODE_PRIVATE).edit {
                            putBoolean(C_SHOW_REQUEST, true)
                        }
                        Toast.makeText(context, "Запит надіслано", Toast.LENGTH_LONG).show()
                    }
                }
            }
            Role.VIEWER -> {
                findViewById<MaterialTextView>(R.id.viewerModeText).isGone = false
                val show = getSharedPreferences(C_SP, Context.MODE_PRIVATE).getBoolean(C_SHOW_REQUEST, false)
                if(name == "Дмитро" && show){
                    findViewById<MaterialButton>(R.id.checkRequestButton).apply {
                        isGone = false
                        setOnClickListener {
                            isVisible = false
                            startActivity(
                                Intent(this@RetirementDataActivity, RequestActivity::class.java)
                            )
                        }
                    }
                }
            }
        }

        findViewById<MaterialTextView>(R.id.nameText).text = "Імя: $name"
        findViewById<MaterialTextView>(R.id.surnameText).text = "Прізвище: $surname"
    }

    private fun navigateToSearch(){
        startActivity(
            Intent(this, SearchActivity::class.java)
        )
    }
}