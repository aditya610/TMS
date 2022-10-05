package com.bignerdranch.android.tms.controllers.ui.tablereservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.tms.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableController : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_controller)

    }

}