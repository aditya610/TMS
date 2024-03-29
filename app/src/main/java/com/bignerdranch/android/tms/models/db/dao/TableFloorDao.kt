package com.bignerdranch.android.tms.models.db.dao

import androidx.room.*
//import com.bignerdranch.android.tms.models.entities.FLoorWithTables
import com.bignerdranch.android.tms.models.data.Floor
import com.bignerdranch.android.tms.models.data.Table
import com.bignerdranch.android.tms.models.data.TableSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface TableFloorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(table: Table)

    @Transaction
    suspend fun insertTable(table: Table) {
        if (table.tableNo.toString().isEmpty()) {
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
    suspend fun getTableByTableNo(tableNo: Int): Table

    @Transaction
    @Query("Select * from `TableSummary` where tableFloorNo = (:floorNo)")
    suspend fun getTableListByTableFloorNo(floorNo: Int): List<TableSummary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFloor(floor: Floor)

    @Update
    suspend fun updateFloor(floor: Floor)

    @Query("Select * from Floor where floorNo =(:floorNo)")
    suspend fun getFloorByFLoorNo(floorNo: Int): Floor

    @Query("Select Count(*) from FLoor")
    fun getFLoorCount(): Flow<Int>

    @Query("Select floorNo from Floor")
    fun getListOfFLoorNo(): Flow<List<Int>>

    @Query("Select floorRows from Floor where floorNo = :floorNo")
    suspend fun getRowFromFloor(floorNo: Int): Int

    @Query("Select floorColumns from Floor where floorNo = :floorNo")
    suspend fun getColumnFromFloor(floorNo: Int): Int

    @Query("Select Count(*) from `table`where tableStatus = (:status)")
    suspend fun getTableCountByStatus(status: String): Int

    @Query("Select Count(*) from `table`")
    fun getTableCount():Flow<Int>

    @Query("Select Count(*) from `table`")
    suspend fun getTables(): Int

    @Query("Select SUM(tableCapacity) from `table`")
    suspend fun getTotalCapacity(): Int

    @Query("Select SUM(tableCapacity) from `table` where tableStatus = (:status)")
    suspend fun getCapacityByStatus(status: String): Int

}