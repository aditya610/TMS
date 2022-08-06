package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.tms.R

class TableDetailFragment : Fragment() {

    private lateinit var editTable: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_table_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTable = view.findViewById(R.id.edit_table)
        editTable.setOnClickListener({
                it.findNavController().navigate(R.id.action_nav_table_to_nav_dialog)
            })
    }
}