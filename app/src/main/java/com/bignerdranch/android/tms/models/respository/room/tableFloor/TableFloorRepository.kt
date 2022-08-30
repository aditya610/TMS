package com.bignerdranch.android.tms.models.respository.room.tableFloor

import androidx.lifecycle.LiveData
import com.bignerdranch.android.tms.models.entities.Floor
import com.bignerdranch.android.tms.models.entities.Table
import com.bignerdranch.android.tms.models.entities.User
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class TableFloorRepository @Inject constructor(
    private val tableFloorDao: TableFloorDao
    ) {

    suspend fun insertTable(table: Table) = tableFloorDao.insertTable(table)

    suspend fun updateTable(table: Table) = tableFloorDao.updateTable(table)

    suspend fun getTableByTableNo(tableNo: Int) = tableFloorDao.getTableByTableNo(tableNo)

    suspend fun insertFloor(floor: Floor) = tableFloorDao.insertFloor(floor)

    suspend fun updateFloor(floor: Floor) = tableFloorDao.updateFloor(floor)

     fun getTablesByFloorNo(floorNo: Int) = tableFloorDao.getTablesByFloorNo(floorNo)

    suspend fun getFloorByFloorNo(floorNo: Int) = tableFloorDao.getFloorByFLoorNo(floorNo)

    fun getFloorCount() =  tableFloorDao.getFLoorCount()

    fun getListOfFLoorNo() = tableFloorDao.getListOfFLoorNo()

    fun getRowFromFloor(floorNo: Int) = tableFloorDao.getRowFromFloor(floorNo)

    fun getColumnFromFloor(floorNo: Int) = tableFloorDao.getColumnFromFloor(floorNo)

    //fun getFLoorWithTables() = tableFloorDao.getFLoorWithTables()

}