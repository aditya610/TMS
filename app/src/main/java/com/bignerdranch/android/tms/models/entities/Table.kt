package com.bignerdranch.android.tms.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Table(@PrimaryKey val tableNo: Int,
                  var capacity: Int,
                  var status: String,
                  var floorNo: Int,
                  var x: Int,
                  var y: Int,
                  var time: Int//LocalDateTime //MutableList<LocalDateTime>
)