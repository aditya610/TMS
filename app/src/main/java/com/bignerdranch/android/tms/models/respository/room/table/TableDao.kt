package com.bignerdranch.android.tms.models.respository.room.table

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.bignerdranch.android.tms.models.entities.Table

@Dao
interface TableDao {

    @Insert
    suspend fun addTable(table: Table)

    @Update
    suspend fun updateTable(table: Table)


}