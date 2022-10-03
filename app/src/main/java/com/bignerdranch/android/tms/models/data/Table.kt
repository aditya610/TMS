package com.bignerdranch.android.tms.models.data

import androidx.room.*

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
    val tableFloorNo: Int,
    val tableRow: Int,
    val tableColumn: Int,
    val tableCapacity: Int,
    val tableStatus: String
)