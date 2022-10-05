package com.bignerdranch.android.tms.controllers.ui.tablereservation.editdialog.tablesetting

import androidx.lifecycle.*
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.models.data.Table
import com.bignerdranch.android.tms.models.repository.TableFloorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
//import com.bignerdranch.android.tms.models.entities.FLoorWithTables
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class TableSettingViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository
) : ViewModel() {

    var tableNo:Int = 0

    var tableRow:Int = 1

    var tableColumn:Int = 1

    var tableSeatingCapacity:Int = 2

    lateinit var  tableFLoorRowList: List<Int>

    lateinit var  tableFloorColumnList: List<Int>

    val  floorNoList: StateFlow<List<Int>> = tableFloorRepository.getListOfFLoorNo().stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5_000), emptyList())

    private var _tableFloorNo = MutableStateFlow(1)
    val tableFloorNo:StateFlow<Int> = _tableFloorNo


    private var _tableFloorRow = MutableStateFlow<Int>(1)
    val tableFloorRow:StateFlow<Int> = _tableFloorRow

    private var _tableFloorColumn = MutableStateFlow<Int>(1)
    val tableFloorColumn:StateFlow<Int> = _tableFloorColumn

    fun setTableFloorNo(floorNo: Int)
    {
        _tableFloorNo.value = floorNo
    }

     fun intialize(){
        viewModelScope.launch(Dispatchers.IO) {
            tableFloorNo.mapLatest {
                _tableFloorColumn.value = tableFloorRepository.getColumnFromFloor(it)
                _tableFloorRow.value = tableFloorRepository.getRowFromFloor(it)
            }.collect()
        }
    }


    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                tableFloorRepository.insertTable(Table(
                    tableNo,
                    tableFloorNo.value,
                    tableRow,
                    tableColumn,
                    tableSeatingCapacity,
                    SeedData.Status.FREE.status
                ))
            }
            catch (e: RuntimeException) {
                e.printStackTrace()
            }
        }
    }

}


//        tableFloorNo.transformLatest {
//        emitAll( tableFloorRepository.getRowFromFloor(it))
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000),0)

//        tableFloorNo.transformLatest {
//        emitAll(tableFloorRepository.getColumnFromFloor(it))
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000),0)

// var  floorNoList123: StateFlow<List<Int>> = tableFloorRepository.getListOfFLoorNo123().
//stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())