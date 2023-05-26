package com.example.jornadaandroid2023.view.hints

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jornadaandroid2023.model.source.remote.entities.Hint
import com.example.jornadaandroid2023.R

class HintsListAdapter(private val items: List<Hint>) : RecyclerView.Adapter<HintsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Hint) {

            val tvHintName = itemView.findViewById<TextView>(R.id.tvHintName)

            tvHintName.text = "Dica ${item.id} : ${item.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.hint_item, parent, false)

        return ViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }


    override fun getItemCount(): Int {
        return items.size
    }

}
