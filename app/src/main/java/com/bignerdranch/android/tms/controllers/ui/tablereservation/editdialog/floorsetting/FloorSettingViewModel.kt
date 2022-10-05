package com.bignerdranch.android.tms.controllers.ui.tablereservation.editdialog.floorsetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.tms.models.data.Floor
import com.bignerdranch.android.tms.models.repository.TableFloorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FloorSettingViewModel @Inject constructor(val repository: TableFloorRepository) :
    ViewModel() {

    var floorNo: Int = 1

    var noOfRowsFloor: Int = 1

    var noOfColumsFloor: Int = 1

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFloor(
                Floor(
                    floorNo,
                    noOfRowsFloor,
                    noOfColumsFloor
                )
            )
        }
    }
}