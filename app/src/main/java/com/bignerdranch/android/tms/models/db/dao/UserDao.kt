package com.bignerdranch.android.tms.models.db.dao

import androidx.room.*
import com.bignerdranch.android.tms.models.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE mobileNo=(:mobileNo) ")
    suspend fun getUserByMobileNo(mobileNo: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUserList(): List<User>
}
