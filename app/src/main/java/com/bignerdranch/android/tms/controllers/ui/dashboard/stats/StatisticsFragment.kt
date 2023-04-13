package com.bignerdranch.android.tms.controllers.ui.dashboard.stats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.ui.tablereservation.TableController
import com.bignerdranch.android.tms.databinding.FragmentStatisticsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment() {

    private val viewModel: StatsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.initialize()
        val binding = DataBindingUtil.inflate<FragmentStatisticsBinding>(
            inflater,R.layout.fragment_statistics, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setPie(10,20,binding.anyChartTable)

        viewModel.tableLeft.observe(
            viewLifecycleOwner,
            Observer {
                //setPie(viewModel.tableLeft.value!! ,viewModel.tableOccupied.value!! , binding.anyChartTable)
            }
        )

        viewModel.tableOccupied.observe(
            viewLifecycleOwner,
            Observer {
                //setPie(viewModel.tableLeft.value!! ,viewModel.tableOccupied.value!! , binding.anyChartTable)
            }
        )
        binding.editStats.setOnClickListener({
            val intent = Intent(context, TableController::class.java)
            startActivity(intent)
        })
        return binding.root
    }

    fun setPie(p1: Int, p2: Int, anyChartTable: AnyChartView)
    {
        if (p1!= null && p2!= null){
            val pieColorChart = arrayOf("#550CAE", "#3044E8")
            val pie = AnyChart.pie();
            val pieData = mutableListOf<ValueDataEntry>()
            pieData.add(ValueDataEntry(getString(R.string.table_left), p1))
            pieData.add(ValueDataEntry(getString(R.string.table_Occupied), p2))
            pie.data(pieData.toList())
            pie.palette(pieColorChart)
            anyChartTable.setChart(pie)
            }
    }

}


