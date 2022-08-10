package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.bignerdranch.android.tms.R

class TableSettingFragment : Fragment() , AdapterView.OnItemSelectedListener
{
    lateinit var floorNo: Spinner
    lateinit var capacity: Spinner
    lateinit var row: Spinner
    lateinit var coloum: Spinner

    enum class Digits(val Number:Int) {
        one(1),
        two(2),
        three(3)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_table_settings, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        setSpinnerApapter(floorNo)
        setSpinnerApapter(capacity)
        setSpinnerApapter(row)
        setSpinnerApapter(coloum)
    }

    private fun initialize(view:View){
        floorNo = view.findViewById(R.id.floor_No)
        capacity =view.findViewById(R.id.capacity)
        row = view.findViewById(R.id.row)
        coloum = view.findViewById(R.id.column)
    }

    private fun setSpinnerApapter(spinner: Spinner){
        spinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            R.id.spinner_text,
            Digits.values().map {
                it.Number
            }
        )
        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}


