package com.bignerdranch.android.tms.controllers.ui.reservationList

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.tms.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationListController: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_list)
    }
}