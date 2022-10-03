package com.bignerdranch.android.tms.controllers.ui.dashboard.reservationlist

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.ui.reservation.ReservationViewModel
import com.bignerdranch.android.tms.controllers.ui.reservation.ResevationListRecyclerAdapter
import com.bignerdranch.android.tms.controllers.ui.tablereservation.tablelist.repeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReservationListFragment : Fragment(R.layout.fragment_reservation_list) {

    private val viewModel: ReservationViewModel by viewModels()
    private lateinit var rvReservationList: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rvReservationList = view.findViewById(R.id.rvreservationslist)
        viewModel.intialize()
        repeatWithViewLifecycle {
            launch {
                viewModel.userList.collect {
                    val adapter = ResevationListRecyclerAdapter(it)
                    rvReservationList.layoutManager = LinearLayoutManager(context)
                    rvReservationList.adapter = adapter
                }
            }
        }
    }
}