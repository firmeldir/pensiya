package com.vladhanin.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vladhanin.myapplication.models.Income


/**Created by Maxim Sutkovenko on 22, June, 2021  **/

class IncomeAdapter : RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {

    private var data: List<Income> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(
            parent
                .context
        ).inflate(R.layout.item_income, parent, false)

        return IncomeAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            money.text = data[position].money
            date.text = data[position].date
            organization.text = data[position].organization
            this.position.text = data[position].position
        }
    }

    fun add(date: List<Income>) {
        this.data = date
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date = view.findViewById<TextView>(R.id.date)
        val money = view.findViewById<TextView>(R.id.money)
        val organization = view.findViewById<TextView>(R.id.organization)
        val position = view.findViewById<TextView>(R.id.position)
    }

}