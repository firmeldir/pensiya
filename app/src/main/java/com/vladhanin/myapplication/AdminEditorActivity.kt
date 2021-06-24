package com.vladhanin.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.vladhanin.myapplication.models.User

class AdminEditorActivity : AppCompatActivity() {

    companion object {

        var editUser: User? = null

        fun start(activity: AppCompatActivity, user: User) {
            this.editUser = user
            activity.startActivity(Intent(activity, AdminEditorActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_editor)

        val edName = findViewById<EditText>(R.id.ed_name)
        val edSurname = findViewById<EditText>(R.id.ed_surname)
        val edPassportId = findViewById<EditText>(R.id.ed_passportId)
        val save = findViewById<Button>(R.id.save)

        edName.setText(editUser?.name)
        edSurname.setText(editUser?.surname)
        edPassportId.setText(editUser?.passportId)

        val rv = findViewById<RecyclerView>(R.id.ed_jobs)
        rv.adapter = JobEditAdapter(editUser?.jobs ?: mutableListOf())

        save.setOnClickListener {
            editUser?.name = edName.text.toString()
            editUser?.surname = edSurname.text.toString()
            editUser?.passportId = edPassportId.text.toString()
        }
    }
}