package com.bignerdranch.android.tms.controllers.ui.tablereservation.tablelist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.controllers.ui.reservation.ReservationController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableListViewPagerFragment : Fragment(), View.OnClickListener {
    private lateinit var viewPagerTable: ViewPager2
    private lateinit var leftButton: ImageButton
    private lateinit var rightButton: ImageButton
    private lateinit var exitButton: Button
    private lateinit var floorNo: TextView
    private val viewModel: TableListViewModel by viewModels()
    private lateinit var adapter: TableListViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allocateViews(view)
        adapter = TableListViewPagerAdapter(this, 1)
        viewPagerTable.adapter = adapter
        viewPagerTable.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setFloorNo()
            }
        })
        setFloorNo()
        onButtonClick()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.floorNoList.collect {
                        if (it.size != 0) {
                            viewModel.list = it
                        }
                        setFloorNo()
                    }
                }

                launch {
                    viewModel.floorCount.collect {
                        viewModel.pageCount = it
                        adapter.updateCount(viewModel.pageCount)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun onButtonClick() {
        rightButton.setOnClickListener(this)
        leftButton.setOnClickListener(this)
        exitButton.setOnClickListener(this)
    }

    private fun allocateViews(view: View) {
        leftButton = view.findViewById(R.id.left_icon)
        rightButton = view.findViewById(R.id.right_icon)
        floorNo = view.findViewById(R.id.floor_no)
        viewPagerTable = view.findViewById(R.id.view_pager)
        exitButton = view.findViewById(R.id.btnexit)
    }

    override fun onClick(v: View?) {
        when (v) {
            leftButton -> if (viewPagerTable.currentItem > 0) viewPagerTable.currentItem -= 1
            rightButton -> if (viewPagerTable.currentItem < viewModel.pageCount) viewPagerTable.currentItem += 1
            exitButton -> startActivity(Intent(context, ReservationController::class.java))
        }
    }

    fun setFloorNo() {
        floorNo.text = viewModel.list.elementAt(viewPagerTable.currentItem).toString()
        viewModel.setCurrenFloorNo(viewModel.list.elementAt(viewPagerTable.currentItem))
    }

}

class TableListViewPagerAdapter(fragment: Fragment, var count: Int) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        if (count == 0)
            return 1
        else
            return count
    }

    fun updateCount(position: Int) {
        count = position
    }

    override fun createFragment(position: Int): Fragment {
        return TableListRecyclerFragment.create()
    }


}