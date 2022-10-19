package com.bignerdranch.android.tms.controllers.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.ui.reservation.ReservationController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardController : AppCompatActivity() {

    private lateinit var addReservation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        addReservation = findViewById(R.id.dashboard_add_reservation)
        addReservation.setOnClickListener({
            val intent = Intent(this, ReservationController::class.java)
            startActivity(intent)
            this.overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left)
        })
    }

    override fun onBackPressed() {

    }
}