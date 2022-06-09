package com.bignerdranch.android.tms.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.models.entities.User
import com.bignerdranch.android.tms.services.ReservationServices
import com.bignerdranch.android.tms.services.viewModel.UserDetailViewModel

private const val TAG = "ReservationController"

class ReservationController : AppCompatActivity() {

    private lateinit var reservationName: EditText
    private lateinit var reservationMobileNo: EditText
    private lateinit var reservationGroup: EditText
    private lateinit var reservationEmailId: EditText
    private lateinit var reservationSpecialRequirement: EditText
    private lateinit var reservationServices: ReservationServices
    private lateinit var addReservation : Button
    private lateinit var user: User
    private val userDetailViewMode: UserDetailViewModel by lazy {
        ViewModelProvider(this).get(UserDetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_form)

        allocatingViews()

        getUser()

        reservationServices = ReservationServices()

        reservationMobileNo.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d(TAG,"")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    reservationServices.searchReservationByMobileNo(
                        s.toString().toLong(),
                        userDetailViewMode,
                    )
                }
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG,"")
            }

        })

        addReservation.setOnClickListener({
            assignUser()
            reservationServices.addReservation(user,userDetailViewMode)
            Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()
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
            reservationEmailId.text.toString(),
            reservationSpecialRequirement.text.toString(),
            reservationGroup.text.toString().toInt(),
            getString(R.string.reservation_status_waiting)
        )
    }

    private fun getUser()
    {
        userDetailViewMode.userLiveData.observe(
            this,
            Observer { user ->
                user?.let {
                    Log.d(TAG,user.name.toString())
                }

            }
        )
    }
}