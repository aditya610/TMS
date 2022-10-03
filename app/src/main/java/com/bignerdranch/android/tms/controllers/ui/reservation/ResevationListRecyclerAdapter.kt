package com.bignerdranch.android.tms.controllers.ui.reservation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.ui.reservation.ReservationHolder
import com.bignerdranch.android.tms.models.data.User

class ResevationListRecyclerAdapter(val list: List<User>) :
    RecyclerView.Adapter<ReservationHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_reservations, parent, false)
        return ReservationHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationHolder, position: Int) {
        holder.name.text = list[position].name
        holder.mobileNo.text = list[position].mobileNo.toString()
        holder.status.text = list[position].status
        holder.group.text = list[position].groupSize.toString()

    }

    override fun getItemCount(): Int = list.size
}

class ReservationHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.tvname)
    val mobileNo: TextView = view.findViewById(R.id.tvmobileno)
    val status: TextView = view.findViewById(R.id.tvstatus)
    val group: TextView = view.findViewById(R.id.tvgroup)

}
