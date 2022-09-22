package com.bignerdranch.android.tms.controllers.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.view.WindowCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.models.entities.Table
import com.bignerdranch.android.tms.services.viewModel.FloorViewModel
import com.bignerdranch.android.tms.services.viewModel.TableViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableSettingDialogFragment : DialogFragment(R.layout.dialog_fragment_table_settings) , AdapterView.OnItemSelectedListener
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

    private val tableViewModel: TableViewModel by hiltNavGraphViewModels(R.id.nav_dialog)
    //private val floorViewModel: FloorViewModel by viewModels()

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

        return super.onCreateView(inflater, container, savedInstanceState)
    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        tableViewModel.intialize()
        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                tableViewModel.tableFloorColumn.collect{

                }
            }
        }
//
//        val themedContext = ContextThemeWrapper(
//            requireContext(),
//            R.style.ThemeOverlay_Trackr_TaskEdit
//        )
//        val dialog = MaterialAlertDialogBuilder(themedContext)
//            .setCancelable(false)
//            .setOnKeyListener { _, keyCode, event ->
//                // This is the only way to intercept the back button press in DialogFragment.
//                if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
//                    findNavController().popBackStack(R.id.nav_dialog, true)
//                    true
//                } else false
//            }
//            .create()
//        lifecycleScope.launch{
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
//                tableViewModel.tableFloorColumn.collect{
//
//                }
//            }
//        }
//        return dialog.apply {
//            setView(findViewById(R.layout.dialog_fragment_table_settings))
//            WindowCompat.setDecorFitsSystemWindows(requireNotNull(window), false)
//        }

        this.isCancelable = false
        val dialog =super.onCreateDialog(savedInstanceState)
        dialog.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                findNavController().popBackStack(R.id.nav_dialog, true)
                true
            } else false
        }
        return dialog.apply {
            WindowCompat.setDecorFitsSystemWindows(requireNotNull(window), false)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.intialize()

        initialize(view)
//                viewLifecycleOwner.lifecycleScope.launch(){
//            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
//                tableViewModel.tableFloorColumn.collect{
//
//                }
//            }
//        }
//
//        lifecycleScope.launch{
//            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
//                launch {
//                    floorViewModel.floorNoList.collect {
//                        setSpinnerApapter(floorNo, it)
//                    }
//                }
//                launch {
//                    tableViewModel.tableFloorColumn.collect{
//
//                        tableViewModel.tableFloorColumnList = getListToN(it)
//                        setSpinnerApapter(coloum,tableViewModel.tableFloorColumnList)
//                    }
//
//                }
//                launch {
//                    tableViewModel.tableFloorRow.collect{
//                        tableViewModel.tableFLoorRowList = getListToN(it)
//                        setSpinnerApapter(row,tableViewModel.tableFLoorRowList)
//                    }
//
//                }
//            }
//        }
//        setSpinnerApapter(capacity,tableViewModel.tableCapacityList)

        button.setOnClickListener({

            if (tableNo.text.isEmpty()){
                tableNo.error = getString(R.string.required)
            }

//            else if(tableRadioGroup.checkedRadioButtonId == -1){
//                Toast.makeText(context,getString(R.string.toast_message_for_radio_button), Toast.LENGTH_SHORT).show()
//            }

            else{

                //viewModel.tableNo = tableNo.text.toString().toInt()

                tableViewModel.save()

                //tableNo.text.clear()

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
//                R.id.table_floor_no -> floorViewModel.floorNoList.value?.elementAt(position)
//                    ?.let { tableViewModel.setTableFloorNo(it) }
//                R.id.table_row -> tableViewModel.tableRow = tableViewModel.tableFLoorRowList.elementAt(position)
//                R.id.table_column -> tableViewModel.tableColumn = tableViewModel.tableFloorColumnList.elementAt(position)
//                R.id.table_seating_capacity -> tableViewModel.tableSeatingCapacity = tableViewModel.tableCapacityList.elementAt(position)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}


