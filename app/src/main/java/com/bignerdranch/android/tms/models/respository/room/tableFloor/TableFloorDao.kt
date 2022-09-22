package com.bignerdranch.android.tms.models.respository.room.tableFloor

import androidx.lifecycle.LiveData
import androidx.room.*
//import com.bignerdranch.android.tms.models.entities.FLoorWithTables
import com.bignerdranch.android.tms.models.entities.Floor
import com.bignerdranch.android.tms.models.entities.Table
import com.bignerdranch.android.tms.models.entities.TableSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface TableFloorDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(table: Table)

    @Transaction
    suspend fun insertTable(table: Table) {
        if(table.tableNo.toString().isEmpty())
        {
            throw IllegalArgumentException("Task must include non-empty title.")
        }
        val data = Table(
            table.tableNo,
            table.tableFloorNo,
            table.tableRow,
            table.tableColumn,
            table.tableCapacity,
            table.tableStatus
        )
        insert(data)
    }

    @Update
    suspend fun updateTable(table: Table)

    @Query("Select * from `Table` where tableNo = (:tableNo)")
    suspend fun getTableByTableNo(tableNo : Int): Table

    @Transaction
    @Query("Select * from `TableSummary` where tableFloorNo = (:floorNo)")
       fun getTableSummaryByTableFloorNo(floorNo: Int): Flow<List<TableSummary>>

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
     suspend fun getColumnFromFloor(floorNo: Int): Int
//
//    @Transaction
//    @Query("SELECT * FROM Floor ")
//    fun getFLoorWithTables(): Flow<List<FLoorWithTables>>

}