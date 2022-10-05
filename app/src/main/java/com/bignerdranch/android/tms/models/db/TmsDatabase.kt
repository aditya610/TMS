package com.bignerdranch.android.tms.models.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignerdranch.android.tms.models.data.Floor
import com.bignerdranch.android.tms.models.data.Table
import com.bignerdranch.android.tms.models.data.TableSummary
import com.bignerdranch.android.tms.models.data.User
import com.bignerdranch.android.tms.models.db.dao.TableFloorDao
import com.bignerdranch.android.tms.models.db.dao.UserDao

@Database(
    entities = [User::class, Table::class, Floor::class],
    views = [TableSummary::class],
    version = 9,
    exportSchema = false

)
@TypeConverters(TmsDatabaseTypeConverters::class)
abstract class TmsDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun tableFLoorDao(): TableFloorDao
}