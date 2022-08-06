package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bignerdranch.android.tms.R

class TableListFragment : Fragment() {

    private lateinit var tableRecyclerView: RecyclerView
    private var adapter: TableListAdapter = TableListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tableRecyclerView = view.findViewById(R.id.table_list_recycler_view) as RecyclerView
        tableRecyclerView.layoutManager = GridLayoutManager(context,3)
        tableRecyclerView.adapter =adapter
    }

    companion object {
        fun create(): Fragment {
            val fragment = TableListFragment()
            return fragment
        }
    }
}


