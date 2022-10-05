package com.bignerdranch.android.tms.models.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(
    tableName = "User",
    foreignKeys = [
        ForeignKey(
            childColumns = ["tableNo"],
            entity = Table::class,
            parentColumns = ["tableNo"]
        )
    ],
    indices = [
        Index("tableNo")
    ]
)
data class User(
    @PrimaryKey var mobileNo: Long,
    var name: String,
    var emailId: String = "none",
    var specificRequirement: String = "none",
    var groupSize: Int = 1,
    var status: String = "Null",
    var time: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date()),
    var tableNo: Int = 0,
    var noOfVisits: Int = 0,
    val id: UUID = UUID.randomUUID()
) {
}