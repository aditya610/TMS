package com.bignerdranch.android.tms.models.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Floor"
)
data class Floor(
    @PrimaryKey
    val floorNo: Int,
    var floorRows: Int,
    var floorColumns: Int
) {

}