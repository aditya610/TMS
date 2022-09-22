package com.bignerdranch.android.tms.services.common

import androidx.room.Insert
import com.bignerdranch.android.tms.models.respository.room.tableFloor.TableFloorDao
import javax.inject.Inject

class GetTableForFloor @Inject constructor(
    private val dao: TableFloorDao
) {
    operator  fun invoke(id: Int) = dao.getTableSummaryByTableFloorNo(id)
}