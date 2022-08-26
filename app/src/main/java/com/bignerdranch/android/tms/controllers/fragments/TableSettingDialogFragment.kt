package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.services.viewModel.TableViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableSettingDialogFragment : Fragment() , AdapterView.OnItemSelectedListener
{
    private lateinit var tableNo: EditText
    private lateinit var floorNo: Spinner
    private lateinit var capacity: Spinner
    private lateinit var row: Spinner
    private lateinit var coloum: Spinner
    private lateinit var button: Button
    private lateinit var addRadioButton: RadioButton
    private lateinit var deleteRadioButton: RadioButton
    private lateinit var tableRadioGroup: RadioGroup

    private val viewModel: TableViewModel by viewModels()

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
        val view = inflater.inflate(R.layout.dialog_fragment_table_settings, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize(view)

        viewModel.floorNoList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                setSpinnerApapter(floorNo,it)
            }

        )

        viewModel.tableFloorRow.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                viewModel.tableFLoorRowList = getListToN(it)
                setSpinnerApapter(row,viewModel.tableFLoorRowList)
            }
        )

        viewModel.tableFloorColumn.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                viewModel.tableFloorColumnList = getListToN(it)
                setSpinnerApapter(coloum,viewModel.tableFloorColumnList)
            }
        )
        setSpinnerApapter(capacity,viewModel.tableCapacityList)

        button.setOnClickListener({

            if (tableNo.text.isEmpty()){
                tableNo.error = getString(R.string.required)
            }

            else if(tableRadioGroup.checkedRadioButtonId == -1){
                Toast.makeText(context,getString(R.string.toast_message_for_radio_button), Toast.LENGTH_SHORT).show()
            }

            else{
                viewModel.tableNo = tableNo.text.toString().toInt()
                viewModel.save()
                tableNo.text.clear()
            }

        })

    }

    private fun getListToN(n: Int): MutableList<Int>{

        val list = mutableListOf<Int>()
        for( i in 1..n){
            list.add(i)
        }
        return list
    }

    private fun initialize(view:View){
        tableNo = view.findViewById(R.id.table_no)
        floorNo = view.findViewById(R.id.table_floor_no)
        capacity =view.findViewById(R.id.table_seating_capacity)
        row = view.findViewById(R.id.table_row)
        coloum = view.findViewById(R.id.table_column)
        addRadioButton =view.findViewById(R.id.table_add)
        deleteRadioButton = view.findViewById(R.id.table_delete)
        button = view.findViewById(R.id.btn_table_setting)
        tableRadioGroup = view.findViewById(R.id.tabe_radio_group)
    }

    private fun setSpinnerApapter(spinner: Spinner, data: List<Int>){

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
            when(parent.id){
                R.id.table_floor_no -> viewModel.floorNoList.value?.elementAt(position)
                    ?.let { viewModel.setTableFloorNo(it) }
                R.id.table_row -> viewModel.tableRow = viewModel.tableFLoorRowList.elementAt(position)
                R.id.table_column -> viewModel.tableColumn = viewModel.tableFloorColumnList.elementAt(position)
                R.id.table_seating_capacity -> viewModel.tableSeatingCapacity = viewModel.tableCapacityList.elementAt(position)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}


