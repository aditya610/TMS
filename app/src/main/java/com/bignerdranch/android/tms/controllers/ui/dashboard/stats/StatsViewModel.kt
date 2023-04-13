package com.bignerdranch.android.tms.controllers.ui.dashboard.stats

import androidx.lifecycle.*
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.models.repository.TableFloorRepository
import com.bignerdranch.android.tms.models.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private var _tableLeft = MutableLiveData<Int>(1)
    val tableLeft = _tableLeft

    private var _tableOccupied = MutableLiveData<Int>(1)
    val tableOccupied = _tableOccupied

    val totalCapacity = MutableStateFlow("")
    val capacityOccupied = MutableStateFlow("")
    val capacityLeft = MutableStateFlow("")
    val totalReservations = MutableStateFlow("")
    val reservationLeft = MutableStateFlow("")
    val reservationCompleted = MutableStateFlow("")


    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = tableFloorRepository.getTables()
            if (data == null || data == 0)
            {
                tableFloorRepository.insertFloor(SeedData.floorData)
                tableFloorRepository.insertTable(SeedData.tableData)
            }
            totalCapacity.value = tableFloorRepository.getTotalCapacity().toString()
            capacityOccupied.value = tableFloorRepository.getCapacityByStatus(SeedData.Status.RESERVE.status).toString()
            capacityLeft.value = tableFloorRepository.getCapacityByStatus(SeedData.Status.FREE.status).toString()
            totalReservations.value = userRepository.getTotalReservation().toString()
            reservationLeft.value = userRepository.getReservationCountByStatus(SeedData.Status.WAITING.status).toString()
            reservationCompleted.value = userRepository.getReservationCountByStatus(SeedData.Status.RESERVE.status).toString()
            val tableLeft = tableFloorRepository.getTableCountByStatus(SeedData.Status.FREE.status)
            _tableLeft.postValue(tableLeft)
            val tableOccupied = tableFloorRepository.getTableCountByStatus(SeedData.Status.RESERVE.status)
            _tableOccupied.postValue(tableOccupied)

        }
    }
}