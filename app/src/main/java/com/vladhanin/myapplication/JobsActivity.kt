package com.vladhanin.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.vladhanin.myapplication.models.Job
import com.vladhanin.myapplication.models.User

class JobsActivity : AppCompatActivity() {

    companion object {
        var user: User? = null

        fun start(activity: AppCompatActivity, user: User) {
            this.user = user
            activity.startActivity(Intent(activity, JobsActivity::class.java))
        }
    }

    private lateinit var rv: RecyclerView
    private val adapter: JobAdapter get() = rv.adapter as JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs)
        rv = findViewById(R.id.jobs)
        rv.adapter = JobAdapter(Data.CURRENT_USER?.jobs ?: listOf())

        val add = findViewById<Button>(R.id.add)
        val edOrg = findViewById<EditText>(R.id.ed_organization)
        val edPosition = findViewById<EditText>(R.id.ed_position)
        val edPeriod = findViewById<EditText>(R.id.ed_period)

        add.setOnClickListener {
            val org = edOrg.text.toString()
            val pos = edPosition.text.toString()
            val per = edPeriod.text.toString()

            val job = Job(position = pos, organization = org, period = per)
            Data.CURRENT_USER?.jobs?.add(0, job)
            adapter.notifyDataSetChanged()
        }
    }


}