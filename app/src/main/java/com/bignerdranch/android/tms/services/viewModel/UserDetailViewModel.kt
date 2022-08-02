package com.bignerdranch.android.tms.services.viewModel

import androidx.lifecycle.*
import com.bignerdranch.android.tms.models.entities.User
import com.bignerdranch.android.tms.models.respository.room.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel() {

    private val userRepository = UserRepository.get()
    private val mobileNoLiveData = MutableLiveData<Long>()

    var userLiveData: LiveData<User> =
        Transformations.switchMap(mobileNoLiveData) {   mobileNo ->
            userRepository.getUserByMobileNo(mobileNo)
        }

    fun getUserByMobileNo(mobileNo: Long)
    {
        mobileNoLiveData.value = mobileNo
    }

    fun addUser(user: User)
    {
        viewModelScope.launch {
            val data: LiveData<User> = userRepository.getUserByMobileNo(user.mobileNo)
            if (data == null)
            {
                userRepository.addUser(user)
            }
            else {

                userRepository.updateUser(user)
            }
        }
    }


}