package com.bignerdranch.android.tms.models.respository.room.user

import androidx.lifecycle.LiveData
import com.bignerdranch.android.tms.models.entities.User
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userDao: UserDao
){
    fun getUserByMobileNo(mobileNo: Long) = userDao.getUserByMobileNo(mobileNo)

    suspend fun addUser(user: User) = userDao.addUser(user)
    
    suspend fun updateUser(user: User) = userDao.updateUser(user)

}