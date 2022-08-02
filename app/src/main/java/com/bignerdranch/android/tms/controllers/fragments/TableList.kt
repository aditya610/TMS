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
import com.bignerdranch.android.tms.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TableList.newInstance] factory method to
 * create an instance of this fragment.
 */
class TableList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var tableRecyclerView: RecyclerView
    private var adapter: TableListAdapter = TableListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_table_list, container, false)
        tableRecyclerView = view.findViewById(R.id.table_list_recycler_view) as RecyclerView
        tableRecyclerView.layoutManager = GridLayoutManager(context,3)
        tableRecyclerView.adapter =adapter
        return view
    }

    private inner class TableHolder(view: View): RecyclerView.ViewHolder(view){
        private val table: ImageButton = view.findViewById(R.id.table)
        fun bind(position: Int) {
            if (position % 2 == 0)
            {
                table.setImageResource(R.drawable.tablefor2)
            }
            else {
                table.setImageResource(R.drawable.tablefor4)
            }
        }


    }

    private inner class TableListAdapter(): RecyclerView.Adapter<TableHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
            val view = layoutInflater.inflate(R.layout.list_item_table, parent, false)
            return TableHolder(view)
        }

        override fun onBindViewHolder(holder: TableHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int = 20

    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment TableList.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            TableList().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}