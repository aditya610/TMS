package com.bignerdranch.android.tms.controllers.ui.tablereservation.tabledetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.databinding.FragmentTableDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableDetailFragment : Fragment() {

    private val viewModel: TableDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.intialize()
        val binding = DataBindingUtil.inflate<FragmentTableDetailBinding>(
            inflater,
            R.layout.fragment_table_detail,
            container,
            false

        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.editTable.setOnClickListener({
            it.findNavController().navigate(R.id.action_nav_table_to_nav_dialog)
        })
        return binding.root
    }
}