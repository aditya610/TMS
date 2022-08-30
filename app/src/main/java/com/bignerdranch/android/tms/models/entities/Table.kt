package com.bignerdranch.android.tms.models.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Time
import java.time.LocalDateTime

@Entity
data class Table(@PrimaryKey val tableNo: Int,
                  var tableFloorNo: Int,
                  var tableRow: Int,
                  var tableColumn: Int,
                  var tableCapacity: Int,
                  var tableStatus: String
)

//data class FLoorWithTables(
//    @Embedded val floor: Floor,
//    @Relation(
//        parentColumn = "floorNo",
//        entityColumn = "tableFloorNo"
//    )
//    val tables: List<Table>
//)