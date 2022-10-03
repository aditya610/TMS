package com.bignerdranch.android.tms.models.data

import androidx.room.DatabaseView
import androidx.room.Embedded


@DatabaseView(
    """
        SELECT 
        t.tableNo , t.tableFloorNo , t.tableRow , t.tableColumn , t.tableCapacity , t.tableStatus,
        f.floorNo AS floor_floorNo, f.floorColumns AS floor_floorColumns, f.floorRows AS floor_floorRows
        FROM `table` AS t
        INNER JOIN `floor` AS f ON f.floorNo = t.tableFloorNo 

    """
)
data class TableSummary(
    val tableNo: Int,
    var tableFloorNo: Int,
    var tableRow: Int,
    var tableColumn: Int,
    var tableCapacity: Int,
    var tableStatus: String,
    @Embedded(prefix = "floor_")
    val floor: Floor
)