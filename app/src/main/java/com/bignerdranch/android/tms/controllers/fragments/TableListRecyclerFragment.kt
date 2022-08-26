package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tms.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableListRecyclerFragment() : Fragment() {

    private lateinit var tableRecyclerView: RecyclerView
    private var adapter: TableListRecyclerAdapter = TableListRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view__table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tableRecyclerView = view.findViewById(R.id.table_list_recycler_view) as RecyclerView
       // Log.d("FloorNo",floorNo.toString())
        tableRecyclerView.layoutManager = GridLayoutManager(context,3)
        tableRecyclerView.adapter =adapter
    }

    companion object {
        fun create(): Fragment {
            val fragment = TableListRecyclerFragment()
            return fragment
        }
    }
}


