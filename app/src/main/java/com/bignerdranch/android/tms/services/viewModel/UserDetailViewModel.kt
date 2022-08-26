package com.bignerdranch.android.tms.services.viewModel

import androidx.lifecycle.*
import com.bignerdranch.android.tms.models.entities.User
import com.bignerdranch.android.tms.models.respository.room.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
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

    fun addUser(user: User) {
        viewModelScope.launch {
            val data: LiveData<User> = userRepository.getUserByMobileNo(user.mobileNo).asLiveData()
            if (data == null) {
                userRepository.addUser(user)
            }
            else {
                userRepository.updateUser(user)
            }
        }
    }

}