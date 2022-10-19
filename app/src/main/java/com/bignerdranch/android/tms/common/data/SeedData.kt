package com.bignerdranch.android.tms.common.data

import com.bignerdranch.android.tms.models.data.Floor
import com.bignerdranch.android.tms.models.data.Table

object SeedData {

    fun gettablepostion(row: Int, column: Int): Map<Int, String> {
        var position = 0;
        var map = mutableMapOf<Int, String>()
        for (i in 1..row)
            for (j in 1..column) {
                map.put(position, i.toString() + j.toString())
                position += 1
            }
        if (map != null && map.size != 0)
            return map
        else
            return mutableMapOf<Int, String>(0 to "11")
    }

    val reservationKey = "mobileNo"
    val TableActivity = "Table"
    val DashBoardActivity = "DashBoard"
    val positionList = listOf(1, 2, 3)

    enum class Digits(val Number: Int) {
        one(1),
        two(2),
        three(3)
    }

    enum class Status(val status: String) {
        FREE("Free"),
        RESERVE("Reserve"),
        WAITING("Waiting")
    }


    fun getListToN(n: Int): MutableList<Int> {
        val list = mutableListOf<Int>()
        for (i in 1..n) {
            list.add(i)
        }
        return list
    }

    val tableCapacityList = listOf<Int>(2, 4, 6)

    val tabTitles = arrayListOf("Table", "Floor")

    val pieChartColorArray = arrayOf("#550CAE","#3044E8")

    val tableData = Table(1,1,1,1,2,"Free")
    val floorData = Floor(1,3,3)

}