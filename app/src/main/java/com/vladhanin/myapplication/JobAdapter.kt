package com.vladhanin.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vladhanin.myapplication.models.Job


/**Created by Maxim Sutkovenko on 24, June, 2021  **/

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
