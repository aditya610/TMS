package com.bignerdranch.android.tms.controllers.ui.dashboard.reservationlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.ui.reservation.ReservationController
import com.bignerdranch.android.tms.controllers.ui.reservation.ReservationViewModel
import com.bignerdranch.android.tms.controllers.ui.reservation.ResevationListRecyclerAdapter
import com.bignerdranch.android.tms.controllers.ui.tablereservation.tablelist.repeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReservationListFragment : Fragment(R.layout.fragment_reservation_list) {

    private val viewModel: ReservationListViewModel by viewModels()
    private lateinit var rvReservationList: RecyclerView
    private lateinit var addReservation: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.intialize()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        intializeViews(view)
        repeatWithViewLifecycle {
            launch {
                viewModel.userList.collect {
                    val adapter = ResevationListRecyclerAdapter(it)
                    rvReservationList.layoutManager = LinearLayoutManager(context)
                    rvReservationList.adapter = adapter
                }
            }
        }
        addReservation.setOnClickListener({
            val intent = Intent(context, ReservationController::class.java)
            startActivity(intent)
        })
    }

    private fun intializeViews(view: View) {
        addReservation = view.findViewById(R.id.list_reservation_add_reservation)
        rvReservationList = view.findViewById(R.id.rvreservationslist)

    }

    override fun onResume() {
        viewModel.intialize()
        super.onResume()
    }
}