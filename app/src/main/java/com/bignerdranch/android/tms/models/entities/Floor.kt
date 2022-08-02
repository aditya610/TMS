package com.bignerdranch.android.tms.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Floor(@PrimaryKey val floorNo: Int,
                 var length: Int,
                 var width: Int,
                 var tableNoList: Int//MutableList<Int>
){

}