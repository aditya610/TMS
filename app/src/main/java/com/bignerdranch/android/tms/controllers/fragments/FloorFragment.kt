package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.tms.R

class FloorFragment : Fragment() , View.OnClickListener
{
    private lateinit var viewPagerTable: ViewPager2
    private lateinit var leftButton: ImageButton
    private lateinit var rightButton: ImageButton
    private lateinit var floorNo: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_floor, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allocateViews(view)
        viewPagerTable.adapter = FloorAdapter(this)
        viewPagerTable.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setFloorNo()
            }
        })
        setFloorNo()
        onButtonClick()
    }

    private fun onButtonClick()
    {
        rightButton.setOnClickListener(this)
        leftButton.setOnClickListener(this)
    }

    private fun allocateViews(view:View){
        leftButton = view.findViewById(R.id.left_icon)
        rightButton = view.findViewById(R.id.right_icon)
        floorNo = view.findViewById(R.id.floor_no)
        viewPagerTable = view.findViewById(R.id.view_pager)
    }

    override fun onClick(v: View?) {
        when(v){
            leftButton -> if(viewPagerTable.currentItem > 0) viewPagerTable.currentItem -= 1
            rightButton -> if(viewPagerTable.currentItem < 4) viewPagerTable.currentItem += 1

        }
    }

    fun setFloorNo()
    {
        floorNo.text = viewPagerTable.currentItem.toString()

    }

}

class FloorAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return TableListFragment.create()
    }


}