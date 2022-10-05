package com.bignerdranch.android.tms.controllers.ui.dashboard.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.models.repository.TableFloorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository
):ViewModel(){
    private var _tableLeft = MutableStateFlow<Int>(1)
    val tableLeft = _tableLeft

    private var _tableOccupied = MutableStateFlow<Int>(1)
    val tableOccupied = _tableOccupied

    fun initialize(){
        viewModelScope.launch {
            //_tableLeft.value = tableFloorRepository.getTableCountByStatus(SeedData.Status.FREE.status)
            //_tableOccupied.value = tableFloorRepository.getTableCountByStatus(SeedData.Status.RESERVE.status)
        }
    }
}