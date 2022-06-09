package com.bignerdranch.android.tms.services.viewModel

import androidx.lifecycle.*
import com.bignerdranch.android.tms.models.entities.User
import com.bignerdranch.android.tms.models.respository.repo.UserRepository
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
            userRepository.addUser(user)
        }
    }


}