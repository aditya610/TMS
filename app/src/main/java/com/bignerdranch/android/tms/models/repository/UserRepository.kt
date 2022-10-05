package com.bignerdranch.android.tms.models.repository

import com.bignerdranch.android.tms.models.data.User
import com.bignerdranch.android.tms.models.db.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getUserByMobileNo(mobileNo: Long) = userDao.getUserByMobileNo(mobileNo)

    suspend fun addUser(user: User) = userDao.addUser(user)

    suspend fun updateUser(user: User) = userDao.updateUser(user)

    suspend fun getUserList() = userDao.getUserList()

}