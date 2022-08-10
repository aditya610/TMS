package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bignerdranch.android.tms.R

class FloorSettingFragment() :Fragment(),AdapterView.OnItemSelectedListener {

    lateinit var row: Spinner
    lateinit var coloum: Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_floor_settings, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(view)
        setSpinnerApapter(row)
        setSpinnerApapter(coloum)
    }

    private fun initialize(view: View){
        row = view.findViewById(R.id.row_floor)
        coloum = view.findViewById(R.id.column_floor)
    }

    private fun setSpinnerApapter(spinner: Spinner){
        spinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            R.id.spinner_text,
            TableSettingFragment.Digits.values().map {
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