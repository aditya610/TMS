package com.bignerdranch.android.tms.controllers.ui.dashboard.stats

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorSpace
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.graphics.toColor
import androidx.core.os.persistableBundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.controllers.ui.tablereservation.TableController
import com.bignerdranch.android.tms.controllers.ui.tablereservation.tablelist.repeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StatisticsFragment : Fragment() {

    private lateinit var anyChartTable: AnyChartView
    private lateinit var editStats: ImageButton
    private val viewModel: StatsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.initialize()
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intializeViews(view)

        val pieColorChart = arrayOf("#550CAE", "#3044E8")
        val pie = AnyChart.pie();
        val pieData = mutableListOf<ValueDataEntry>()
        pieData.add(ValueDataEntry(getString(R.string.table_left), 10))
        pieData.add(ValueDataEntry(getString(R.string.table_Occupied), 20))
        pie.data(pieData.toList())
        pie.palette(pieColorChart)
        anyChartTable.setChart(pie)

        editStats.setOnClickListener({
            val intent = Intent(context, TableController::class.java)
            startActivity(intent)
        })
    }

    private fun intializeViews(view: View) {
        anyChartTable = view.findViewById(R.id.any_chart_table)
        editStats = view.findViewById(R.id.editStats)
    }


}
//repeatWithViewLifecycle {
//    launch {
//        viewModel.tableLeft.collect{
//            pieData.add(ValueDataEntry(getString(R.string.table_left), it))
//            pieData.add(ValueDataEntry(getString(R.string.table_Occupied),viewModel.tableOccupied.value))
//            setChart(pieData,pie)
//        }
//    }
//}

