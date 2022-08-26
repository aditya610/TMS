package com.bignerdranch.android.tms.common.application

import android.app.Application
import com.bignerdranch.android.tms.models.respository.room.user.UserRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TmsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}