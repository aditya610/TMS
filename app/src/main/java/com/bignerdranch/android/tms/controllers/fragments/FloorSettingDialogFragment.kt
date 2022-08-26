package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.models.entities.Floor
import com.bignerdranch.android.tms.services.viewModel.FloorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FloorSettingDialogFragment() :Fragment(),AdapterView.OnItemSelectedListener {


    private lateinit var row: Spinner
    private lateinit var coloum: Spinner
    private lateinit var floorNo: EditText
    private lateinit var button: Button
    private lateinit var addRadioButton: RadioButton
    private lateinit var deleteRadioButton: RadioButton
    private lateinit var addTableToFloorCheckBox: CheckBox
    private lateinit var floorRadioGroup: RadioGroup

    private val dummySpinnerData = arrayListOf<Int>(1,2,3)

    private val viewModel: FloorViewModel by viewModels()

    private lateinit var floor:Floor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.dialog_fragment_floor_settings, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize(view)

        setSpinnerApapter(row)
        setSpinnerApapter(coloum)

        button.setOnClickListener({

            if (floorNo.text.isEmpty()){
                floorNo.error = getString(R.string.required)
            }

            else if(floorRadioGroup.checkedRadioButtonId == -1){
                Toast.makeText(context,getString(R.string.toast_message_for_radio_button), Toast.LENGTH_SHORT).show()
            }

            else{
                assignFloor()
                viewModel.insertOrUpdateFloor(floor)
                floorNo.text.clear()
            }

        })

    }

    private fun assignFloor() {
        floor = Floor(
            floorNo.text.toString().toInt(),
            viewModel.noOfRowsFloor,
            viewModel.noOfColumsFloor
        )
    }

    private fun initialize(view: View){
        row = view.findViewById(R.id.row_floor)
        coloum = view.findViewById(R.id.column_floor)
        floorNo = view.findViewById(R.id.et_floor_no)
        button = view.findViewById(R.id.btn_floor_setting)
        addRadioButton = view.findViewById(R.id.floor_add)
        deleteRadioButton = view.findViewById(R.id.floor_delete)
        addTableToFloorCheckBox = view.findViewById(R.id.add_tables_to_floor)
        floorRadioGroup = view.findViewById(R.id.floor_radio_group)
    }

    private fun setSpinnerApapter(spinner: Spinner){
        spinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            R.id.spinner_text,
            TableSettingDialogFragment.Digits.values().map {
                it.Number
            }
        )
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            when(parent.id){
                R.id.row_floor -> viewModel.noOfRowsFloor = dummySpinnerData[position]
                R.id.column_floor -> viewModel.noOfColumsFloor = dummySpinnerData[position]
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

}