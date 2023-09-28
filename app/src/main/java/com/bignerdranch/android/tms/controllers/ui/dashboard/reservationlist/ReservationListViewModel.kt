package com.bignerdranch.android.tms.controllers.ui.dashboard.reservationlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.tms.models.data.User
import com.bignerdranch.android.tms.models.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList

    fun deleteUser(mobileNo: Long){
        viewModelScope.launch {
            userRepository.deleteUser(mobileNo)
        }
    }

    fun intialize() {
        viewModelScope.launch {
            _userList.value = userRepository.getUserList()
        }
    }
}