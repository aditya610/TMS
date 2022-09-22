package com.bignerdranch.android.tms.models.entities

import androidx.room.*
import java.sql.Time
import java.time.LocalDateTime

@Entity(
    tableName = "Table",
    foreignKeys = [
        ForeignKey(
            childColumns = ["tableFloorNo"],
            entity = Floor::class,
            parentColumns = ["floorNo"]
        )
    ],
    indices = [
        Index("tableFloorNo")
    ]

)
data class Table(
    @PrimaryKey val tableNo: Int,
                  var tableFloorNo: Int,
                  var tableRow: Int,
                  var tableColumn: Int,
                  var tableCapacity: Int,
                  var tableStatus: String
)

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
//data class FLoorWithTables(
//    @Embedded val floor: Floor,
//    @Relation(
//        parentColumn = "floorNo",
//        entityColumn = "tableFloorNo"
//    )
//    val tables: List<Table>
//)