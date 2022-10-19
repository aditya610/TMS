package com.bignerdranch.android.tms.controllers.ui.tablereservation.editdialog.tablesetting

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.common.data.SeedData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableSettingDialogFragment : Fragment(R.layout.dialog_fragment_table_settings),
    AdapterView.OnItemSelectedListener {
    private lateinit var tableNo: EditText
    private lateinit var floorNo: Spinner
    private lateinit var capacity: Spinner
    private lateinit var row: Spinner
    private lateinit var coloum: Spinner
    private lateinit var button: Button
    private lateinit var addRadioButton: RadioButton
    private lateinit var deleteRadioButton: RadioButton
    private lateinit var tableRadioGroup: RadioGroup
    private val tableViewModel: TableSettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tableViewModel.intialize()
        initialize(view)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    tableViewModel.floorNoList.collect {
                        setSpinnerApapter(floorNo, it)
                    }
                }
                launch {
                    tableViewModel.tableFloorColumn.collect {
                        tableViewModel.tableFloorColumnList = SeedData.getListToN(it)
                        setSpinnerApapter(coloum, tableViewModel.tableFloorColumnList)
                    }
                }
                launch {
                    tableViewModel.tableFloorRow.collect {
                        tableViewModel.tableFLoorRowList = SeedData.getListToN(it)
                        setSpinnerApapter(row, tableViewModel.tableFLoorRowList)
                    }
                }
            }
        }
        setSpinnerApapter(capacity, SeedData.tableCapacityList)
        button.setOnClickListener({
            if (tableNo.text.isEmpty()) {
                tableNo.error = getString(R.string.required)
            } else if (tableRadioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(
                    context,
                    getString(R.string.toast_message_for_radio_button),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                tableViewModel.tableNo = tableNo.text.toString().toInt()
                tableViewModel.save()
                tableNo.text.clear()
            }
        })
    }

    private fun initialize(view: View) {
        tableNo = view.findViewById(R.id.table_no)
        floorNo = view.findViewById(R.id.table_floor_no)
        capacity = view.findViewById(R.id.table_seating_capacity)
        row = view.findViewById(R.id.table_row)
        coloum = view.findViewById(R.id.table_column)
        addRadioButton = view.findViewById(R.id.table_add)
        deleteRadioButton = view.findViewById(R.id.table_delete)
        button = view.findViewById(R.id.btn_table_setting)
        tableRadioGroup = view.findViewById(R.id.tabe_radio_group)
    }

    private fun setSpinnerApapter(spinner: Spinner, data: List<Int>) {

        spinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            R.id.spinner_text,
            data
        )
        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        if (parent != null) {
            when (parent.id) {
                R.id.table_floor_no -> tableViewModel.floorNoList.value?.elementAt(position)
                    ?.let { tableViewModel.setTableFloorNo(it) }
                R.id.table_row -> tableViewModel.tableRow =
                    tableViewModel.tableFLoorRowList.elementAt(position)
                R.id.table_column -> tableViewModel.tableColumn =
                    tableViewModel.tableFloorColumnList.elementAt(position)
                R.id.table_seating_capacity -> tableViewModel.tableSeatingCapacity =
                    SeedData.tableCapacityList.elementAt(position)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}


