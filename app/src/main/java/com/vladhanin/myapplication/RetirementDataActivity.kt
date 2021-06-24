package com.vladhanin.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.vladhanin.myapplication.models.User
import java.text.SimpleDateFormat
import java.util.*

class RetirementDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retirement_data)

        val userLogin: String? = intent.extras?.getString(C_LOGIN)
        val user: User = userLogin?.let { Data.USERS.find { it.login == userLogin }!! } ?: Data.CURRENT_USER!!

        findViewById<MaterialTextView>(R.id.nameText).text = "Ім'я: " + user.name
        findViewById<MaterialTextView>(R.id.surnameText).text = "Прізвище: " + user.surname
        findViewById<MaterialTextView>(R.id.pensionIdText).text = "Номер пенсійної справи: " + user.pensionId
        findViewById<MaterialTextView>(R.id.dateOfBirthdayText).text = "Дата народження: ${SimpleDateFormat("yyyy-mm-dd").format(user.dateOfBirthday)}"
        findViewById<MaterialTextView>(R.id.officialAddressText).text = "Адреса реєстрації: " + user.officialAddress
        findViewById<MaterialTextView>(R.id.actualAddressText).text = "Адреса фактичного місця проживання: " + user.actualAddress

        //if pensioner
        user.pension?.let {
            findViewById<MaterialTextView>(R.id.border).isGone = false
            findViewById<MaterialTextView>(R.id.pensionTypeText).text = it.type
            findViewById<MaterialTextView>(R.id.pensionOrganizationText).text = it.organization
            findViewById<MaterialTextView>(R.id.pensionDateText).text = it.date
            findViewById<MaterialButton>(R.id.requestForPensionButton).isEnabled = false
        }

        if(user.pension != null || ((Date().time - user.dateOfBirthday.time) / 31_536_000_000L) < 65){
            findViewById<MaterialButton>(R.id.requestForPensionButton).isEnabled = false
        }

        findViewById<MaterialButton>(R.id.requestForPensionButton).apply {
            setOnClickListener {
                Toast.makeText(context, "Запит надіслано", Toast.LENGTH_LONG).show()
                if(user.login == Data.TEST_USER.login){
                    getSharedPreferences(C_SP, Context.MODE_PRIVATE).edit { putBoolean(C_SHOW_REQUEST, true) }
                }
            }
        }

        when{
            user != Data.CURRENT_USER -> {
                findViewById<MaterialTextView>(R.id.modeText).text = "viewer mode"
                findViewById<MaterialButton>(R.id.requestForPensionButton).isGone = true
                if(getSharedPreferences(C_SP, Context.MODE_PRIVATE).getBoolean(C_SHOW_REQUEST, false) && user.login == Data.TEST_USER.login) {
                    findViewById<MaterialButton>(R.id.checkRequestButton).apply {
                        isGone = false
                        setOnClickListener {
                            isVisible = false
                            startActivity(Intent(this@RetirementDataActivity, RequestActivity::class.java))
                        }
                    }
                }
                findViewById<MaterialButton>(R.id.adminEditorButton).apply {
                    isGone = false
                    setOnClickListener{
                        AdminEditorActivity.start(this@RetirementDataActivity, user)
                    }
                }
                findViewById<MaterialButton>(R.id.jobsButton).setOnClickListener { JobsActivity.start(this, user) }
                findViewById<MaterialButton>(R.id.incomeButton).setOnClickListener { IncoreActivity.start(this, user) }
            }
            user.isAdmin -> {
                findViewById<MaterialTextView>(R.id.modeText).text = "admin mode"

                findViewById<MaterialButton>(R.id.allUsersDataButton).apply {
                    isGone = false
                    setOnClickListener{
                        startActivity(Intent(this@RetirementDataActivity, SearchActivity::class.java))
                    }
                }
                findViewById<MaterialButton>(R.id.jobsButton).setOnClickListener {startActivity(Intent(this, JobsActivity::class.java)) }
                findViewById<MaterialButton>(R.id.incomeButton).setOnClickListener { startActivity(Intent(this, IncoreActivity::class.java)) }
            }
            else -> {
                findViewById<MaterialTextView>(R.id.modeText).text = ""
                findViewById<MaterialButton>(R.id.jobsButton).setOnClickListener {startActivity(Intent(this, JobsActivity::class.java)) }
                findViewById<MaterialButton>(R.id.incomeButton).setOnClickListener { startActivity(Intent(this, IncoreActivity::class.java)) }
            }
        }
    }
}