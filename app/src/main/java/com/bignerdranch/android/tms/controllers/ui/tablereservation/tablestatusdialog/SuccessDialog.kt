package com.bignerdranch.android.tms.controllers.ui.tablereservation.tablestatusdialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.ui.dashboard.DashboardController

class SuccessDialog: DialogFragment(R.layout.dialog_success) {
    private lateinit var btnSuccess:Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSuccess = view.findViewById(R.id.btn_success)
        btnSuccess.setOnClickListener({
            startActivity(Intent(context, DashboardController::class.java))
        })
    }


}