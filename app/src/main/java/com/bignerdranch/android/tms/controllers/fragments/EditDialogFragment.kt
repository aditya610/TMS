package com.bignerdranch.android.tms.controllers.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.tms.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditDialogFragment : DialogFragment(R.layout.dialog_fragment_table_settings) {

    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private var tabTitles = arrayListOf("Table", "Floor")
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
////        super.onCreateView(inflater, container, savedInstanceState)
////        val view = inflater.inflate(R.layout.dialog_fragment_edit, container, false)
////        return view
//    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        this.isCancelable = false
        val dialog=super.onCreateDialog(savedInstanceState)
        dialog.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                findNavController().popBackStack(R.id.nav_dialog, true)
                true
            } else false
        }
        return dialog.apply {
            WindowCompat.setDecorFitsSystemWindows(requireNotNull(window), false)
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewPager2 = view.findViewById(R.id.viewPager2)
//        tabLayout = view.findViewById(R.id.tabLayout)
//
//        viewPager2.adapter = FloorDialogAdapter(this, 2)
//
//        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
//            tab.text = tabTitles[position]
//        }.attach()
//    }

    class FloorDialogAdapter(fragment: DialogFragment, var position: Int) :
        FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return position
        }

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return TableSettingDialogFragment()
                1 -> return FloorSettingDialogFragment()
            }
            return TableSettingDialogFragment()
        }
    }

}