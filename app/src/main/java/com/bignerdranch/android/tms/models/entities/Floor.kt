package com.bignerdranch.android.tms.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Floor(@PrimaryKey
                 val floorNo: Int,
                 var floorRows: Int,
                 var floorColumns: Int
){

}