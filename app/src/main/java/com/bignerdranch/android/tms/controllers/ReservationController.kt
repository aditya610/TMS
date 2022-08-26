package com.bignerdranch.android.tms.controllers

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.models.entities.User
import com.bignerdranch.android.tms.services.viewModel.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ReservationController"

@AndroidEntryPoint
class ReservationController : AppCompatActivity() {

    private lateinit var reservationName: EditText
    private lateinit var reservationMobileNo: EditText
    private lateinit var reservationGroup: EditText
    private lateinit var reservationEmailId: EditText
    private lateinit var reservationSpecialRequirement: EditText
    private lateinit var addReservation : Button
    private lateinit var user: User
    private val userDetailViewMode: UserDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_form)
        allocatingViews()

        reservationMobileNo.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d(TAG,"")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    //userDetailViewMode.setUserMobileNo(s.toString().toLong())
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG,"")
            }

        })

        addReservation.setOnClickListener({
            if (reservationMobileNo.text.toString().isEmpty()) {
                reservationMobileNo.error = getString(R.string.required)
            }
            else if (reservationName.text.toString().isEmpty()) {
                    reservationName.error = getString(R.string.required)
            }
            else if (reservationGroup.text.toString().isEmpty()) {
                    reservationGroup.error = getString(R.string.required)
            }
            else {
                assignUser()
                userDetailViewMode.addUser(user)
                Toast.makeText(this, "Reservation Added", Toast.LENGTH_SHORT).show()
                Handler().postDelayed(Runnable {
                    val intent = Intent(this, TableController::class.java)
                    startActivity(intent)
                }, 1)
            }
        })
    }

    private fun allocatingViews(){
        reservationName = findViewById(R.id.reservation_name)
        reservationMobileNo = findViewById(R.id.reservation_mobile_no)
        reservationGroup = findViewById(R.id.reservation_group)
        reservationEmailId = findViewById(R.id.reservation_email_id)
        reservationSpecialRequirement = findViewById(R.id.reservation_requirements)
        addReservation = findViewById(R.id.add_reservation)
    }

    private fun assignUser()
    {
        user = User(
            reservationMobileNo.text.toString().toLong(),
            reservationName.text.toString(),
            reservationEmailId.text.toString().ifEmpty {""} ,
            reservationSpecialRequirement.text.toString().ifEmpty {""},
            reservationGroup.text.toString().toInt(),
            getString(R.string.reservation_status_waiting)
        )
    }

}