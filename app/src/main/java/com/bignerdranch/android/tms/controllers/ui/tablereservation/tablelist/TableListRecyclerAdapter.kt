package com.bignerdranch.android.tms.controllers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.controllers.ui.tablereservation.tablelist.TableListViewPagerFragmentDirections
import com.bignerdranch.android.tms.models.data.TableSummary

class TableListRecyclerAdapter(val map: Map<Int, TableSummary>) :
    RecyclerView.Adapter<TableHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_table, parent, false)
        return TableHolder(view)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {

        if (map.get(position) != null) {
            if (map.get(position)!!.tableNo != 0) {
                holder.bind(position, map.get(position)!!)
            } else {
                holder.bind2()
            }
        }
        holder.itemView.setOnClickListener({
            if (map.get(position) != null) {
                val action = TableListViewPagerFragmentDirections.actionFloorFragmentToNavDialog(
                    map.get(position)!!.tableNo
                )
                it.findNavController().navigate(action)
            }
        })
    }

    override fun getItemCount(): Int = 9

}

class TableHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val table: ImageButton = view.findViewById(R.id.table)

    fun bind(position: Int, tableSummary: TableSummary) {
        if (tableSummary.tableCapacity == 2) {
            if (tableSummary.tableStatus == SeedData.Status.RESERVE.status) {
                table.setImageResource(R.drawable.tablefor2)
            } else {
                table.setImageResource(R.drawable.tablefor2free)
            }
        } else {
            if (tableSummary.tableStatus == SeedData.Status.RESERVE.status) {
                table.setImageResource(R.drawable.tablefor4)
            } else {
                table.setImageResource(R.drawable.tablefor4free)
            }
        }
    }

    fun bind2() {
        table.setImageResource(R.drawable.tablefor2)
        table.visibility = View.INVISIBLE

    }

}

