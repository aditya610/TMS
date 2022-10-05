package com.bignerdranch.android.tms.controllers.ui.tablereservation.tablelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.tms.models.data.Floor
import com.bignerdranch.android.tms.models.data.TableSummary
import com.bignerdranch.android.tms.models.repository.TableFloorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableListViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository
) : ViewModel() {

    var list: List<Int> = listOf(0, 1)

    var pageCount: Int = 1

    private var _currentFloorNo = MutableStateFlow<Int>(1)

    val currentFloorNo = _currentFloorNo

    var _tableList = MutableStateFlow<List<TableSummary>>(emptyList())

    val tableList = _tableList

    val floorNoList: StateFlow<List<Int>> = tableFloorRepository.getListOfFLoorNo().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000), emptyList()
    )

    val floorCount: StateFlow<Int> = tableFloorRepository.getFloorCount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000), 1
    )

    fun setCurrenFloorNo(floorNo: Int) {
        _currentFloorNo.value = floorNo
    }

    fun intialize() {
        viewModelScope.launch() {
            currentFloorNo.mapLatest {
                _tableList.value = tableFloorRepository.getTableListByTableFloorNo(it)
            }.collect()
        }
    }

    val refreshTableList = tableFloorRepository.getTableCount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        1
    )
}


// _tableList.value = getTableForFloor(1)
//            getTableForFloor(1).collect{
//                _tableList.value = it
//            }
//            getTableForFloor(1).onEach{
//                _tableList.value = it
//            }
//_floorNoList.value = tableFloorRepository.getListOfFLoorNo()
//_floorCount.value = tableFloorRepository.getFloorCount()
//
//        getTableForFloor(1).onEach{
//            _tableList.value = it
//        }.launchIn(viewModelScope)
//
//    val tableList = combine(currentFloorNo,getTableForFloor(1)){
//        a,b -> b
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

//    val tableList = currentFloorNo.transformLatest { id ->
//        emitAll(tableFloorRepository.getTableSummaryByTableFloorNo(id))
//    }.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5_000), emptyList())