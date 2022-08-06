package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.tms.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class EditDialogFragment : DialogFragment(R.layout.dialog_fragment_edit)
{
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private var tabTitles = arrayListOf("Table","Floor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager2 = view.findViewById(R.id.viewPager2)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager2.adapter = FloorDialogAdapter(this,2)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

}

class FloorDialogAdapter(fragment: Fragment,var position: Int): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return position
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return TableSettingFragment()
            1 -> return FloorSettingFragment()
        }
        return TableSettingFragment()
    }

}