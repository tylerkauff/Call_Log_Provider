package com.example.calllog

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PhoneLogAdapter internal constructor(context: Context?, data: List<Call>) :
    RecyclerView.Adapter<PhoneLogAdapter.ViewHolder>() {
    private val mData: List<Call> = data
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.call_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val call = mData[position]
        holder.numberTextView.text = call.number
        val durationFormat = call.duration + " seconds"
        holder.durationTextView.text = durationFormat

        val typeString: String = when (call.type) {
            "0" -> "Declined"
            "1" -> "Incoming"
            "2" -> "Outgoing"
            "3" -> "Missed"
            else -> { "Declined" }
        }

        holder.typeTextView.text = typeString
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var numberTextView: TextView = view.findViewById(R.id.tvNumber)
        var durationTextView: TextView = view.findViewById(R.id.tvDuration)
        var typeTextView: TextView = view.findViewById(R.id.tvItem)

    }

}