package com.bignerdranch.android.tms.common.application

import android.app.Application
import com.bignerdranch.android.tms.models.respository.repo.UserRepository


class TmsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        UserRepository.intialize(this)
    }

}