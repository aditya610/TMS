package com.bignerdranch.android.tms.models.respository.room.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.tms.models.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE mobileNo=(:mobileNo) ")
    fun getUserByMobileNo(mobileNo: Long): Flow<User>

    @Insert
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}
