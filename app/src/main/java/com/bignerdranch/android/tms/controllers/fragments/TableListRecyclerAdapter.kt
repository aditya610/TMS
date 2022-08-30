package com.bignerdranch.android.tms.controllers.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.models.entities.Table

class TableListRecyclerAdapter(val map:Map<Int,Table>): RecyclerView.Adapter<TableHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_table, parent, false)
        return TableHolder(view)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        //holder.bind(position)
        if (map.get(position) != null)
        {
            if (map.get(position)!!.tableNo != 0)
            {
                holder.bind(position)
            }
            else{
                holder.bind2(position)
            }
        }
    }

    override fun getItemCount(): Int = 9

}

class TableHolder(view: View): RecyclerView.ViewHolder(view){

    private val table: ImageButton = view.findViewById(R.id.table)

    fun bind(position: Int) {
        if (position % 2 == 0) {
            table.setImageResource(R.drawable.tablefor2)
        }
        else {
            table.setImageResource(R.drawable.tablefor4)
        }
    }

    fun bind2(position: Int) {
            table.setImageResource(R.drawable.tablefor2)
            table.visibility = View.INVISIBLE

    }

}