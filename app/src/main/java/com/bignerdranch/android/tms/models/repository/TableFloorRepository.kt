package com.bignerdranch.android.tms.models.repository

import com.bignerdranch.android.tms.models.data.Floor
import com.bignerdranch.android.tms.models.data.Table
import com.bignerdranch.android.tms.models.db.dao.TableFloorDao
import javax.inject.Inject

class TableFloorRepository @Inject constructor(
    private val tableFloorDao: TableFloorDao
) {

    suspend fun insertTable(table: Table) = tableFloorDao.insertTable(table)

    suspend fun updateTable(table: Table) = tableFloorDao.updateTable(table)

    suspend fun insertFloor(floor: Floor) = tableFloorDao.insertFloor(floor)

    suspend fun getTableByTableNo(tableNo: Int) = tableFloorDao.getTableByTableNo(tableNo)

    suspend fun getTableListByTableFloorNo(floorNo: Int) =
        tableFloorDao.getTableListByTableFloorNo(floorNo)

    fun getFloorCount() = tableFloorDao.getFLoorCount()

    fun getListOfFLoorNo() = tableFloorDao.getListOfFLoorNo()

    suspend fun getRowFromFloor(floorNo: Int) = tableFloorDao.getRowFromFloor(floorNo)

    suspend fun getColumnFromFloor(floorNo: Int) = tableFloorDao.getColumnFromFloor(floorNo)

    fun getTableCount() = tableFloorDao.getTableCount()

    suspend fun getTables() = tableFloorDao.getTables()

    suspend fun getTotalCapacity() = tableFloorDao.getTotalCapacity()

    suspend fun getCapacityByStatus(status: String) = tableFloorDao.getCapacityByStatus(status)

    suspend fun getTableCountByStatus(status: String) =tableFloorDao.getTableCountByStatus(status)

}