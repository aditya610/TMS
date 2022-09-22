package com.bignerdranch.android.tms.controllers.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter.POSITION_NONE
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.tms.R
import com.bignerdranch.android.tms.common.data.SeedData
//import com.bignerdranch.android.tms.models.entities.FLoorWithTables
import com.bignerdranch.android.tms.services.viewModel.FloorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableListViewPagerFragment : Fragment() , View.OnClickListener
{
    private val TAG = "TableListTag"
    private lateinit var viewPagerTable: ViewPager2
    private lateinit var leftButton: ImageButton
    private lateinit var rightButton: ImageButton
    private lateinit var floorNo: TextView
    private val viewModel: FloorViewModel by hiltNavGraphViewModels(R.id.floorFragment)
    private lateinit var adapter: TableListViewPagerAdapter

    private var list:List<Int> = listOf(0,1)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onPause() {
        Log.d("pausecheck","onpausetlrf1")
        super.onPause()
        Log.d("pausecheck","onpausetlrfb1")

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"on ViewCreate")
        allocateViews(view)
        adapter = TableListViewPagerAdapter(this,1)
        viewPagerTable.adapter = adapter
        val button = view.findViewById<ImageButton>(R.id.edit_table_floor)
        button.setOnClickListener({
            it.findNavController().navigate(R.id.action_floorFragment_to_nav_dialog)
        })

//        viewPagerTable.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                setFloorNo()
//            }
//        })
        //setFloorNo()
        //onButtonClick()


    //    viewLifecycleOwner.lifecycleScope.launch{
      //      viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
        //        launch {
//                    viewModel.floorNoList.collect{
//                        if(it.size != 0)
//                        {
//                            list = it
//                        }
//                        setFloorNo()
//                    }
                }

//                launch {
//                    viewModel.floorCount.collect{
//                        viewModel.pageCount = it
//                        adapter.updateCount(viewModel.pageCount)
//                        adapter.notifyDataSetChanged()
//
//                    }
//                }
      //      }
    //    }
  //  }

    private fun onButtonClick()
    {
       // rightButton.setOnClickListener(this)
        //leftButton.setOnClickListener(this)
    }

    private fun allocateViews(view:View){
        leftButton = view.findViewById(R.id.left_icon)
        rightButton = view.findViewById(R.id.right_icon)
        floorNo = view.findViewById(R.id.floor_no)
        viewPagerTable = view.findViewById(R.id.view_pager)
    }

    override fun onClick(v: View?) {
//        when(v){
//            leftButton -> if(viewPagerTable.currentItem > 0) viewPagerTable.currentItem -= 1
//            rightButton -> if(viewPagerTable.currentItem < viewModel.pageCount) viewPagerTable.currentItem += 1
//        }
    }

    fun setFloorNo()
    {
        floorNo.text = list.elementAt(viewPagerTable.currentItem).toString()
        //viewModel.setCurrenFloorNo(list.elementAt(viewPagerTable.currentItem))
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