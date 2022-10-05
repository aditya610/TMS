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

    var reservationMobileNo = MutableStateFlow("")

    var reservationName = MutableStateFlow("")

    var noOfPeople = MutableStateFlow("")

    var email = MutableStateFlow("")

    var specialRequirement = MutableStateFlow("")

    fun loadIntialData(mobileNo: Long) {
        viewModelScope.launch {
            if (mobileNo != 0L) {
                val details = userRepository.getUserByMobileNo(mobileNo)
                if (details != null) {
                    reservationMobileNo.value = details.mobileNo.toString()
                    reservationName.value = details.name
                    noOfPeople.value = details.groupSize.toString()
                }
            }
        }
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(
                User(
                    reservationMobileNo.value.toLong(),
                    reservationName.value,
                    email.value,
                    specialRequirement.value,
                    noOfPeople.value.toInt(),
                    SeedData.Status.WAITING.status,
                    tableNo = 1
                )
            )
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