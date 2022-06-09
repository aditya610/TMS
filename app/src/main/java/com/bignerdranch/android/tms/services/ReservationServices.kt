package com.bignerdranch.android.tms.services

import com.bignerdranch.android.tms.models.entities.User
import com.bignerdranch.android.tms.services.viewModel.UserDetailViewModel


class ReservationServices {

    fun searchReservationByMobileNo(mobileNo: Long, userDetailViewModel: UserDetailViewModel) {
        userDetailViewModel.getUserByMobileNo(mobileNo)
    }

    fun addReservation(user: User, userDetailViewModel: UserDetailViewModel) {
        userDetailViewModel.addUser(user)
    }
}