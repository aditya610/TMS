package com.bignerdranch.android.tms.controllers.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.common.data.SeedData
//import com.bignerdranch.android.tms.models.entities.FLoorWithTables
import com.bignerdranch.android.tms.models.entities.Table
import com.bignerdranch.android.tms.models.entities.TableSummary
import com.bignerdranch.android.tms.services.viewModel.FloorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableListRecyclerFragment() : Fragment() {
    private val TAG = "TableFragmentTag"

    private lateinit var tableRecyclerView: RecyclerView
    private lateinit var adapter: TableListRecyclerAdapter
    private val viewModel: FloorViewModel by viewModels()

    private lateinit var tablePostionMap:Map<Int,String>
    private lateinit var map:MutableMap<String,TableSummary>
    private lateinit var finalMap:MutableMap<Int,TableSummary>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.intialize()
        return inflater.inflate(R.layout.recycler_view__table_list, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeatWithViewLifecycle {
            launch() {
                viewModel.tableList.collect {
                }
            }
        }

    }

    override fun onPause() {
        Log.d("pausecheck","onpausetlrf"+lifecycle.currentState.toString())
        super.onPause()
        Log.d("pausecheck","onpausetlrfb")

    }

    companion object {

        fun create(): Fragment {
            val fragment = TableListRecyclerFragment()
            return fragment
        }
    }
}
inline fun Fragment.repeatWithViewLifecycle(
    minState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    if (minState == Lifecycle.State.INITIALIZED || minState == Lifecycle.State.DESTROYED) {
        throw IllegalArgumentException("minState must be between INITIALIZED and DESTROYED")
    }
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minState) {
            block()
        }
    }
}

                    // tableRecyclerView = view.findViewById(R.id.table_list_recycler_view) as RecyclerView
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.tableSummaryList.collect{
//
//                }
//            }
//        }
//        lifecycle.coroutineScope.launch{
//            viewModel.tableSummaryList().collect(){
//
//            }
//        }
                    // viewLifecycleOwner.lifecycleScope.launch{
                    // repeatOnLifecycle(Lifecycle.State.STARTED) {
                    //     launch {
                    //  viewModel.tableSummaryList().collect() {
//                        if (it != null && it.size != 0) {
//
//                            map = mutableMapOf<String, TableSummary>()
//                            finalMap = mutableMapOf<Int, TableSummary>()
//                            tablePostionMap = SeedData.gettablepostion(3, 3)
//
//                            for (i in 0..it.size - 1) {
//                                map.put(
//                                    it[i].tableRow.toString() + it[i].tableColumn.toString(),
//                                    it[i]
//                                )
//                            }
//
//                            for (i in 0..tablePostionMap.size) {
//
//                                if (map.get(tablePostionMap.get(i)) != null) {
//                                    finalMap.put(i, map.get(tablePostionMap.get(i))!!)
//                                } else {
//                                    finalMap.put(i, TableSummary(0, 1, 1, 1, 1, ""))
//                                }
//
//                            }
//
//                            adapter = TableListRecyclerAdapter(finalMap)
//                            tableRecyclerView.layoutManager = GridLayoutManager(context, 3)
//                            tableRecyclerView.adapter = adapter
//
//                        }
                    //}
                    // }
                    // }
                    //}




