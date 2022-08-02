package com.bignerdranch.android.tms.models.respository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.tms.models.entities.Floor
import com.bignerdranch.android.tms.models.entities.Table
import com.bignerdranch.android.tms.models.entities.User
import com.bignerdranch.android.tms.models.respository.room.user.UserDao

@Database(entities = [User::class, Table::class, Floor::class], version = 1)
abstract class TmsDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}