package com.bignerdranch.android.tms.controllers.ui.tablereservation.tabledetail

import androidx.lifecycle.*
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.models.db.dao.TableFloorDao
import com.bignerdranch.android.tms.models.repository.TableFloorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableDetailViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository
) : ViewModel() {

    var totalTable = MutableStateFlow("15")
    var tableLeft = MutableStateFlow("10")

    fun intialize() {
//        viewModelScope.launch {
//            tableFloorRepository.getTableCount().collect{
//                totalTable.value = it.toString()
//            }
//            tableFloorRepository.getTableCountByStatus(SeedData.Status.RESERVE.status).collect {
//                tableLeft.value = it.toString()
//            }
//        }
    }


}