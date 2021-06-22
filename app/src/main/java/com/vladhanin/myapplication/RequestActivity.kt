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

        findViewById<RecyclerView>(R.id.jobs).adapter = JobAdapter(Data.CURRENT_USER?.jobs ?: listOf())
    }
}

class JobAdapter(private val data: List<Job>) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val organization: TextView = view.findViewById(R.id.organization)
        val position: TextView = view.findViewById(R.id.position)
        val period: TextView = view.findViewById(R.id.period)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_job, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            organization.text = data[position].organization
            this.position.text = data[position].position
            period.text = data[position].period
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size

}
