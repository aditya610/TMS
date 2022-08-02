package com.bignerdranch.android.tms.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Entity
data class User(@PrimaryKey var mobileNo: Long,
           var name: String,
           var emailId: String = "none",
           var specificRequirement: String = "none",
           var groupSize: Int = 1,
           var status: String = "Null",
           var time: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()),
           var tableNo: Int = 0,
           var noOfVisits: Int = 0,
           val id: UUID = UUID.randomUUID() )
{
}