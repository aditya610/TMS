package com.bignerdranch.android.tms.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter.POSITION_NONE
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.services.viewModel.FloorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableListViewPagerFragment : Fragment() , View.OnClickListener
{
    private lateinit var viewPagerTable: ViewPager2
    private lateinit var leftButton: ImageButton
    private lateinit var rightButton: ImageButton
    private lateinit var floorNo: TextView
    private val viewModel: FloorViewModel by viewModels()
    private lateinit var adapter: TableListViewPagerAdapter

    private var list:List<Int> = listOf(0,1)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        viewModel.floorCount.observe(
            viewLifecycleOwner,
            Observer {
                viewModel.pageCount = it
                adapter.updateCount(viewModel.pageCount)
                adapter.notifyDataSetChanged()
            }
        )
        viewModel.floorNoList.observe(
            viewLifecycleOwner,
            Observer {
                if(it.size != 0)
                {
                    list = it
                }
                setFloorNo()
            }
        )
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allocateViews(view)
        adapter = TableListViewPagerAdapter(this,viewModel.pageCount)
        viewPagerTable.adapter = adapter
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
            rightButton -> if(viewPagerTable.currentItem < viewModel.pageCount) viewPagerTable.currentItem += 1
        }
    }

    fun setFloorNo()
    {
        floorNo.text = list.elementAt(viewPagerTable.currentItem).toString()
    }

}

class TableListViewPagerAdapter(fragment: Fragment, var count: Int): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        if (count == 0)
            return 1
        else
            return count
    }

    fun updateCount(position: Int){
        count = position
    }

    override fun createFragment(position: Int): Fragment {
        return TableListRecyclerFragment.create()
    }


}