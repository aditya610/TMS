package com.bignerdranch.android.tms.services.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.tms.models.entities.Floor
import com.bignerdranch.android.tms.models.entities.TableSummary
import com.bignerdranch.android.tms.models.respository.room.tableFloor.TableFloorRepository
import com.bignerdranch.android.tms.services.common.GetTableForFloor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FloorViewModel @Inject constructor(
    private val getTableForFloor: GetTableForFloor,
    private val tableFloorRepository: TableFloorRepository
) : ViewModel() {

    var pageCount:Int = 1
    var noOfRowsFloor:Int = 1
    var noOfColumsFloor:Int = 1

    private var _currentFloorNo = MutableStateFlow<Int>(1)
    var currentFloorNo = _currentFloorNo

    fun setCurrenFloorNo(floorNo: Int)
    {
        _currentFloorNo.value = floorNo
    }

    var _tableList = MutableStateFlow<List<TableSummary>>(emptyList())

    val tableList = _tableList
//
//    val tableList = combine(currentFloorNo,getTableForFloor(1)){
//        a,b -> b
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

//    val tableList = currentFloorNo.transformLatest { id ->
//        emitAll(tableFloorRepository.getTableSummaryByTableFloorNo(id))
//    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5_000), emptyList())

    private var _floorNoList = MutableStateFlow<List<Int>>(emptyList())
    var  floorNoList: StateFlow<List<Int>> = tableFloorRepository.getListOfFLoorNo().stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5_000), emptyList())


    private var _floorCount = MutableStateFlow<Int>(1)
    val floorCount: StateFlow<Int> = tableFloorRepository.getFloorCount().stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5_000),1)
    init {

        getTableForFloor(1).onEach{
            _tableList.value = it
        }.launchIn(viewModelScope)

    }
    fun intialize() {
        viewModelScope.launch(Dispatchers.IO) {
            // _tableList.value = getTableForFloor(1)
//            getTableForFloor(1).collect{
//                _tableList.value = it
//            }
//            getTableForFloor(1).onEach{
//                _tableList.value = it
//            }
            //_floorNoList.value = tableFloorRepository.getListOfFLoorNo()
            //_floorCount.value = tableFloorRepository.getFloorCount()
        }

    }


    fun insertOrUpdateFloor(floor: Floor) {

        viewModelScope.launch {
            val data = tableFloorRepository.getFloorByFloorNo(floor.floorNo)
            if (data == null){
                tableFloorRepository.insertFloor(floor)
            }
            else{
                tableFloorRepository.updateFloor(floor)
            }
        }

    }

}