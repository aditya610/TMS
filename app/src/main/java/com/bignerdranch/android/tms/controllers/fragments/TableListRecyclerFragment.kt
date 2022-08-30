package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.common.data.SeedData
//import com.bignerdranch.android.tms.models.entities.FLoorWithTables
import com.bignerdranch.android.tms.models.entities.Table
import com.bignerdranch.android.tms.services.viewModel.FloorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableListRecyclerFragment() : Fragment() {

    private lateinit var tableRecyclerView: RecyclerView
    private lateinit var adapter: TableListRecyclerAdapter
    private val viewModel: FloorViewModel by viewModels({requireParentFragment()})

    private lateinit var tablePostionMap:Map<Int,String>
    private lateinit var map:MutableMap<String,Table>
    private lateinit var finalMap:MutableMap<Int,Table>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view__table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tableRecyclerView = view.findViewById(R.id.table_list_recycler_view) as RecyclerView

    }

    override fun onResume() {

        viewModel.tableList.observe(
            viewLifecycleOwner) {
                if(it != null && it.size != 0) {

                    map = mutableMapOf<String,Table>()
                    finalMap = mutableMapOf<Int,Table>()
                    tablePostionMap = SeedData.gettablepostion(3, 3)

                    for (i in 0..it.size-1) {
                        map.put(it[i].tableRow.toString() + it[i].tableColumn.toString(), it[i])
                    }

                    for (i in 0..tablePostionMap.size) {

                        if (map.get(tablePostionMap.get(i)) != null) {
                            finalMap.put(i, map.get(tablePostionMap.get(i))!!)
                        } else {
                            finalMap.put(i, Table(0, 1, 1, 1, 1, ""))
                        }

                    }

                    adapter = TableListRecyclerAdapter(finalMap)
                    tableRecyclerView.layoutManager = GridLayoutManager(context, 3)
                    tableRecyclerView.adapter = adapter

                }
            }
        super.onResume()
    }

    companion object {
        fun create(): Fragment {
            val fragment = TableListRecyclerFragment()
            return fragment
        }
    }
}


