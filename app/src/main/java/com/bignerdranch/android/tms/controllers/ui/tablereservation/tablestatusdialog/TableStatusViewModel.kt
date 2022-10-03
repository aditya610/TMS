package com.bignerdranch.android.tms.controllers.ui.tablereservation.tablestatusdialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.models.data.Table
import com.bignerdranch.android.tms.models.data.User
import com.bignerdranch.android.tms.models.repository.TableFloorRepository
import com.bignerdranch.android.tms.models.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableStatusViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var tableNo: Int = 0
        set(value) {
            field = value
            loadInitialData(value)
        }
    val tn = MutableStateFlow("")

    val tableStatus = MutableStateFlow("")
    var status: String = ""

    val seatingCapacity = MutableStateFlow("")

    var reservation: Long = 0L

    private fun loadInitialData(tableNo: Int) {
        viewModelScope.launch {
            if (tableNo != 0) {
                val detail = tableFloorRepository.getTableByTableNo(tableNo)
                if (detail != null) {
                    tn.value = detail.tableNo.toString()
                    if (detail.tableStatus != "") {
                        tableStatus.value = detail.tableStatus
                    } else {
                        tableStatus.value = SeedData.Status.FREE.status
                    }
                    seatingCapacity.value = detail.tableCapacity.toString()
                }
            }
        }
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            val detail = tableFloorRepository.getTableByTableNo(tn.value.toInt())
            tableFloorRepository.updateTable(
                Table(
                    detail.tableNo,
                    detail.tableFloorNo,
                    detail.tableRow,
                    detail.tableColumn,
                    detail.tableCapacity,
                    status
                )
            )
        }
    }


    fun updateReservations() {
        viewModelScope.launch {
            if (reservation != 0L) {
                val details = userRepository.getUserByMobileNo(reservation)
                if (details != null) {
                    userRepository.updateUser(
                        User(
                            details.mobileNo,
                            details.name,
                            details.emailId,
                            details.specificRequirement,
                            details.groupSize,
                            SeedData.Status.RESERVE.status,
                            details.time,
                            tn.value.toInt(),
                            details.noOfVisits,
                            details.id
                        )
                    )
                }
            }
        }
    }

}