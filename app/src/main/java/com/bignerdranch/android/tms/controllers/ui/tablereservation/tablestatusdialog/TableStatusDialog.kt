package com.bignerdranch.android.tms.controllers.ui.tablereservation.tablestatusdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.databinding.DialogFragmentTableStatusBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableStatusDialog : DialogFragment(), AdapterView.OnItemSelectedListener {

    private val args: TableStatusDialogArgs by navArgs()
    private val viewModel: TableStatusViewModel by viewModels()
    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = args.tableNo
        viewModel.tableNo = id

        val binding = DataBindingUtil.inflate<DialogFragmentTableStatusBinding>(
            inflater,
            R.layout.dialog_fragment_table_status,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        dataStore = binding.root.context.createDataStore(name = getString(R.string.dataStoreName))
        val dataStoreKey = preferencesKey<String>(getString(R.string.dataStoreKey))
        binding.btnTableFree.setOnClickListener({
            viewModel.save()
            findNavController().popBackStack(R.id.nav_dialog_list, true)
        })

        binding.btnTableReserve.setOnClickListener({
            viewModel.save()
            viewModel.updateReservations()
            findNavController().popBackStack(R.id.nav_dialog_list, true)
        })

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.tableStatus.collect {
                        setView(binding)
                    }
                }
                launch {
                    val preferences = dataStore.data.first()
                    if (preferences[dataStoreKey] != null) {
                        viewModel.reservation = preferences[dataStoreKey]!!.toLong()
                    }
                }
            }
        }
        return binding.root
    }

    private fun setSpinnerApapter(spinner: Spinner, data: List<String>) {
        spinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item_table_status,
            R.id.spinner_text_table_status,
            data
        )
        spinner.onItemSelectedListener = this

    }

    private fun setView(binding: DialogFragmentTableStatusBinding) {
        if (viewModel.tableStatus.value.equals(SeedData.Status.FREE.status)) {
            binding.btnTableFree.visibility = View.INVISIBLE
            binding.btnTableReserve.visibility = View.VISIBLE
            setSpinnerApapter(binding.spTableStatus, listOf("RESERVE"))
            viewModel.status = SeedData.Status.RESERVE.status
        } else {
            binding.btnTableReserve.visibility = View.INVISIBLE
            binding.btnTableFree.visibility = View.VISIBLE
            setSpinnerApapter(binding.spTableStatus, listOf("FREE"))
            viewModel.status = SeedData.Status.FREE.status
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


}