package com.bignerdranch.android.tms.models.respository.room.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.tms.models.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE mobileNo=(:mobileNo) ")
    fun getUserByMobileNo(mobileNo: Long): LiveData<User>

    @Insert
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}
/*
what is life is it not the things we like to do or things what we have to do to survive in this world
and if we get time to do things we like to do we become lazy and push it to another day and another in few
days it becomes a month and months passes and then we take stress that if we are running out of time and
to manage the stress we take another day off and another which become a never ending cycle and in the end we do
things that we have to do to survive . How can one break the cycle ? BY Consistence BY Discipline BY Daily Goals
I think by doing the shit/hard work Consistently 12/04/22 19/06/22lena paul-my daddy diary My step Daddy Secrets
slender youghter and big titted stepmom hot sex story But i don't work in corporate anymore
I building something of my own
 */