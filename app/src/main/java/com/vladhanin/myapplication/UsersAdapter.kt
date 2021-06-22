package com.vladhanin.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.vladhanin.myapplication.models.User

class UsersAdapter(val context: Context, var dataSet: Array<User>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.fullNameText)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = "${dataSet[position].name} ${dataSet[position].surname}"
        viewHolder.itemView.setOnClickListener {
            startActivity(
                context,
                Intent(context, RetirementDataActivity::class.java).apply {
                    putExtra(C_NAME, dataSet[position].name)
                    putExtra(C_SURNAME, dataSet[position].surname)
                    putExtra(C_PASSPORT, dataSet[position].passportId)
                }, null
            )
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}