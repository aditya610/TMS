package com.bignerdranch.android.tms.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.ListFragment
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.fragments.Table
import com.bignerdranch.android.tms.controllers.fragments.TableAdapter
import com.bignerdranch.android.tms.controllers.fragments.TableDetail
import com.bignerdranch.android.tms.controllers.fragments.TableList

class TableController : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_controller)

//        val tableDetailFragment = TableDetail()
        val tableListFragment = Table()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.add(R.id.fragment_table_detail, tableDetailFragment)
        fragmentTransaction.add(R.id.fragment_table_list, tableListFragment)
//
        fragmentTransaction.commit()


//
//        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_table_detail)
//        if (currentFragment == null) {
//            val fragment = TableList()
//            supportFragmentManager.beginTransaction().add(R.id.fragment_table_detail, fragment).commit()
//        }
    }
}