package com.bignerdranch.android.tms.services.viewModel

import androidx.lifecycle.*
import com.bignerdranch.android.tms.models.entities.Floor
import com.bignerdranch.android.tms.models.entities.Table
import com.bignerdranch.android.tms.models.respository.room.tableFloor.TableFloorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository
) : ViewModel() {

    var tableNo:Int = 0
    var tableFloorNo = MutableLiveData<Int>()
    var tableRow:Int = 1
    var tableColumn:Int = 1
    var tableSeatingCapacity:Int = 2
    lateinit var  tableFLoorRowList: List<Int>
    lateinit var  tableFloorColumnList: List<Int>
    val tableCapacityList = listOf<Int>(2,4,6)

    var  floorNoList: LiveData<List<Int>> =
        tableFloorRepository.getListOfFLoorNo().asLiveData()

    var tableFloorRow:LiveData<Int> =
        Transformations.switchMap(tableFloorNo){
            tableFloorRepository.getRowFromFloor(it).asLiveData()
        }

    var tableFloorColumn:LiveData<Int> =
        Transformations.switchMap(tableFloorNo){
            tableFloorRepository.getColumnFromFloor(it).asLiveData()
        }

    fun setTableFloorNo(floorNo: Int)
    {
        tableFloorNo.value = floorNo
    }

    fun save(){
        val data = Table(
            tableNo,
            tableFloorNo.value!!,
            tableRow,
            tableColumn,
            tableSeatingCapacity,
            ""
        )
        insertOrUpdateTable(data)
    }

    fun insertOrUpdateTable(table:Table) {

        viewModelScope.launch {
            val data = tableFloorRepository.getTableByTableNo(table.tableNo)
            if (data == null){
                tableFloorRepository.insertTable(table)
            }
            else{
                tableFloorRepository.updateTable(table)
            }
        }

    }


}

@HiltViewModel
class FloorViewModel @Inject constructor(
    private val tableFloorRepository: TableFloorRepository
) : ViewModel() {

    var pageCount:Int = 1
    var noOfRowsFloor:Int = 1
    var noOfColumsFloor:Int = 1

    var  floorNoList: LiveData<List<Int>> =
        tableFloorRepository.getListOfFLoorNo().asLiveData()

    val floorCount: LiveData<Int> =
        tableFloorRepository.getFloorCount().asLiveData()

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