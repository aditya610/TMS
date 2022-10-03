package com.bignerdranch.android.tms.controllers.ui.reservation

import androidx.lifecycle.*
import com.bignerdranch.android.tms.common.data.SeedData
import com.bignerdranch.android.tms.models.data.User
import com.bignerdranch.android.tms.models.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var mobileNo: String = ""

    var reservationName: String = ""

    var noOfPeople: String = ""

    var email: String = ""

    var specialRequirement: String = ""

    private var _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(
                User(
                    mobileNo.toLong(),
                    reservationName,
                    email,
                    specialRequirement,
                    noOfPeople.toInt(),
                    SeedData.Status.WAITING.status,
                    tableNo = 1
                )
            )
        }
    }

    fun intialize() {
        viewModelScope.launch {
            _userList.value = userRepository.getUserList()
        }
    }

}

//
//    private val mobileNoLiveData = MutableLiveData<Long>()
//
//    var userLiveData: LiveData<User> =
//        Transformations.switchMap(mobileNoLiveData) {   mobileNo ->
//            userRepository.getUserByMobileNo(mobileNo).asLiveData()
//        }
//
//    fun setUserMobileNo(mobileNo: Long) {
//        mobileNoLiveData.value = mobileNo
//    }