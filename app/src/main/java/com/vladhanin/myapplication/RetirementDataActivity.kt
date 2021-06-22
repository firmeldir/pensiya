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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retirement_data)

        val argName: String? = intent.extras?.getString(C_NAME)
        val argSurname: String? = intent.extras?.getString(C_SURNAME)
        val argPassportId: String? = intent.extras?.getString(C_PASSPORT)

        when{
            argName != null -> {
                findViewById<MaterialTextView>(R.id.viewerModeText).isGone = false
                findViewById<MaterialTextView>(R.id.nameText).text = "Імя: $argName"
                findViewById<MaterialTextView>(R.id.surnameText).text = "Прізвище: $argSurname"
                findViewById<MaterialTextView>(R.id.passportIdText).text = "ID паспорту: $argPassportId"

                val show = getSharedPreferences(C_SP, Context.MODE_PRIVATE).getBoolean(C_SHOW_REQUEST, false)
                if(argName == "Йозеф" && show){
                    findViewById<MaterialButton>(R.id.checkRequestButton).apply {
                        isGone = false
                        setOnClickListener {
                            isVisible = false
                            startActivity(Intent(this@RetirementDataActivity, RequestActivity::class.java))
                        }
                    }
                }
            }
            Data.CURRENT_USER!!.isAdmin -> {
                findViewById<MaterialButton>(R.id.searchForRetirementDataButton).apply {
                    isGone = false
                    setOnClickListener{ navigateToSearch() }
                }
                findViewById<MaterialButton>(R.id.allUsersDataButton).apply {
                    isGone = false
                    setOnClickListener{ navigateToSearch() }
                }
                findViewById<MaterialTextView>(R.id.nameText).text = "Імя: ${Data.CURRENT_USER!!.name}"
                findViewById<MaterialTextView>(R.id.surnameText).text = "Прізвище: ${Data.CURRENT_USER!!.surname}"
                findViewById<MaterialTextView>(R.id.passportIdText).text = "ID паспорту: ${Data.CURRENT_USER!!.passportId}"
                findViewById<MaterialTextView>(R.id.adminModeText).isGone = false
            }
            !Data.CURRENT_USER!!.isAdmin -> {
                with(findViewById<MaterialButton>(R.id.requestForPensionButton)){
                    isGone = false
                    setOnClickListener {
                        getSharedPreferences(C_SP, Context.MODE_PRIVATE).edit {
                            putBoolean(C_SHOW_REQUEST, true)
                        }
                        Toast.makeText(context, "Запит надіслано", Toast.LENGTH_LONG).show()
                    }
                }
                findViewById<MaterialTextView>(R.id.nameText).text = "Імя: ${Data.CURRENT_USER!!.name}"
                findViewById<MaterialTextView>(R.id.surnameText).text = "Прізвище: ${Data.CURRENT_USER!!.surname}"
                findViewById<MaterialTextView>(R.id.passportIdText).text = "ID паспорту: ${Data.CURRENT_USER!!.passportId}"
            }
        }
    }

    private fun navigateToSearch(){
        startActivity(
            Intent(this, SearchActivity::class.java)
        )
    }
}