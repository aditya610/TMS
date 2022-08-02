package com.bignerdranch.android.tms.models.respository.room.user

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.tms.models.entities.User
import com.bignerdranch.android.tms.models.respository.room.TmsDatabase

private const val DATABASE_NAME = "tms-database"

class UserRepository private constructor(context: Context){

    private val database: TmsDatabase = Room.databaseBuilder(
        context.applicationContext,
        TmsDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val userDao = database.userDao()

    fun getUserByMobileNo(mobileNo: Long): LiveData<User> = userDao.getUserByMobileNo(mobileNo)

    suspend fun addUser(user: User) = userDao.addUser(user)
    
    suspend fun updateUser(user: User) = userDao.updateUser(user)

    companion object {

        private var INSTANCE : UserRepository? = null

        fun intialize(context: Context)
        {
            if (INSTANCE == null) {
                INSTANCE = UserRepository(context)
            }
        }

        fun get(): UserRepository {
            return INSTANCE ?: throw IllegalStateException("User Repository Must be Intialize")
        }
    }

}