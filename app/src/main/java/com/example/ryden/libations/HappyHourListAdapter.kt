package com.example.ryden.libations

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class HappyHourListAdapter(val context: Context, var happyHourList: ArrayList<MyHappyHour>)
    : RecyclerView.Adapter<HappyHourListAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return happyHourList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(position)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bindItem(position: Int) {
            val nameView =  itemView.findViewById<TextView>(R.id.id_locName)
            val timeView = itemView.findViewById<TextView>(R.id.id_happyTime)
            val locView = itemView.findViewById<TextView>(R.id.id_address)
            val iconView = itemView.findViewById<ImageView>(R.id.id_cardIcon)

            nameView.text = happyHourList[position].locName
            timeView.text = happyHourList[position].times
            locView.text = happyHourList[position].address


            //iconView.setImageResource(icon)
        }
    }
}