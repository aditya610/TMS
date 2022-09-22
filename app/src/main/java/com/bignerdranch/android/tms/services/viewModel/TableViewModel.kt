package com.bignerdranch.android.tms.services.viewModel

import androidx.lifecycle.*
import com.bignerdranch.android.tms.models.entities.Floor
import com.bignerdranch.android.tms.models.entities.Table
import com.bignerdranch.android.tms.models.respository.room.tableFloor.TableFloorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.asLiveData
import com.bignerdranch.android.tms.models.entities.TableSummary
import com.bignerdranch.android.tms.services.common.GetTableForFloor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
//import com.bignerdranch.android.tms.models.entities.FLoorWithTables
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository
) : ViewModel() {

    var tableNo:Int = 0
    var tableRow:Int = 1
    var tableColumn:Int = 1
    var tableSeatingCapacity:Int = 2

    lateinit var  tableFLoorRowList: List<Int>
    lateinit var  tableFloorColumnList: List<Int>

    val tableCapacityList = listOf<Int>(2,4,6)


    private var _tableFloorNo = MutableStateFlow(1)
    var tableFloorNo:StateFlow<Int> = _tableFloorNo
    private var _floorNoList = MutableStateFlow<List<Int>>(emptyList())
   // var  floorNoList123: StateFlow<List<Int>> = tableFloorRepository.getListOfFLoorNo123().
    //stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private var _tableFloorRow = MutableStateFlow<Int>(1)

    var tableFloorRow:StateFlow<Int> = _tableFloorRow
//        tableFloorNo.transformLatest {
//        emitAll( tableFloorRepository.getRowFromFloor(it))
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000),0)

    private var _tableFloorColumn = MutableStateFlow<Int>(1)

    var tableFloorColumn:StateFlow<Int> = _tableFloorColumn
//        tableFloorNo.transformLatest {
//        emitAll(tableFloorRepository.getColumnFromFloor(it))
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000),0)

    fun setTableFloorNo(floorNo: Int)
    {
        _tableFloorNo.value = floorNo
    }

     fun intialize(){
//
        viewModelScope.launch(Dispatchers.IO) {
//            _tableFloorRow.combine(tableFloorNo) { a , b ->
//                tableFloorRepository.getRowFromFloor(b)
//            }.collect {
//                _tableFloorRow.value = it.toString().toInt()
//            }
//
            _tableFloorColumn.value = tableFloorRepository.getColumnFromFloor(1)
//            combine(_tableFloorColumn,tableFloorNo){ a ,b ->
//                tableFloorRepository.getColumnFromFloor(b)
//            }.collect{
//                a.value = it.toString().toInt()
//            }
//
//            //_floorNoList.value = tableFloorRepository.getListOfFLoorNo()
        }
    }



    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                tableFloorRepository.insertTable(Table(
                    4,
                    1,
                    2,
                    1,
                    2,
                    ""
                ))
            }
            catch (e: RuntimeException) {
                e.printStackTrace()
            }
        }
    }
}

