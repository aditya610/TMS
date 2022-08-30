package com.bignerdranch.android.tms.models.respository.room.tableFloor

import androidx.lifecycle.LiveData
import androidx.room.*
//import com.bignerdranch.android.tms.models.entities.FLoorWithTables
import com.bignerdranch.android.tms.models.entities.Floor
import com.bignerdranch.android.tms.models.entities.Table
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface TableFloorDao {

    @Insert
    suspend fun insertTable(table: Table)

    @Update
    suspend fun updateTable(table: Table)

    @Query("Select * from `Table` where tableNo = (:tableNo)")
    suspend fun getTableByTableNo(tableNo : Int): Table

    @Query("Select * from `Table` where tableFloorNo = (:floorNo)")
     fun getTablesByFloorNo(floorNo: Int): Flow<List<Table>>

    @Insert
    suspend fun insertFloor(floor: Floor)

    @Update
    suspend fun updateFloor(floor: Floor)

    @Query("Select * from Floor where floorNo =(:floorNo)")
    suspend fun getFloorByFLoorNo(floorNo : Int): Floor

    @Query("Select Count(*) from FLoor")
    fun getFLoorCount(): Flow<Int>

    @Query("Select floorNo from Floor")
     fun getListOfFLoorNo(): Flow<List<Int>>

     @Query("Select floorRows from Floor where floorNo = :floorNo")
     fun getRowFromFloor(floorNo: Int): Flow<Int>

     @Query("Select floorColumns from Floor where floorNo = :floorNo")
     fun getColumnFromFloor(floorNo: Int): Flow<Int>
//
//    @Transaction
//    @Query("SELECT * FROM Floor ")
//    fun getFLoorWithTables(): Flow<List<FLoorWithTables>>

}