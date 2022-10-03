package com.bignerdranch.android.tms.models.di

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.tms.models.db.TmsDatabase
import com.bignerdranch.android.tms.models.db.dao.TableFloorDao
import com.bignerdranch.android.tms.models.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TmsModule {

    private lateinit var tmsDatabase: TmsDatabase

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): TmsDatabase {
        tmsDatabase = Room.databaseBuilder(
            context,
            TmsDatabase::class.java,
            "tms-database"
        ).fallbackToDestructiveMigration().build()
        return tmsDatabase
    }

    @Provides
    fun provideUserDao(tmsDatabase: TmsDatabase): UserDao {
        return tmsDatabase.userDao()
    }

    @Provides
    fun provideTableFloorDao(tmsDatabase: TmsDatabase): TableFloorDao {
        return tmsDatabase.tableFLoorDao()
    }

}