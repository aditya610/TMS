package com.bignerdranch.android.tms.controllers.ui.dashboard.stats

import android.graphics.Color
import android.graphics.ColorSpace
import android.os.Bundle
import android.view.View
import androidx.core.graphics.toColor
import androidx.core.os.persistableBundleOf
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.bignerdranch.android.tms.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private lateinit var anyChartTable: AnyChartView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val array1 = arrayOf("#550CAE", "#3044E8")
        val pie = AnyChart.pie();
        val data = mutableListOf<ValueDataEntry>()
        data.add(ValueDataEntry("Table Left", 10))
        data.add(ValueDataEntry("Table Occupied", 20))
        pie.data(data.toList())
        //pie.title("Table Percentage chart")

        pie.palette(array1)
        anyChartTable = view.findViewById(R.id.any_chart_table)
        anyChartTable.setChart(pie)
    }
}