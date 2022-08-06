package com.bignerdranch.android.tms.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.fragments.FloorFragment

class TableController : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_controller)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_floor)
        if(currentFragment == null) {
            val floorFragment = FloorFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragment_floor, floorFragment)
            fragmentTransaction.commit()
        }
    }
}