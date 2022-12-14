package com.example.parkspace.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.parkspace.view.fragments.*

class FloorAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{ FirstFloor() }
            1->{ SecondFloor() }
            2->{ ThirdFragment() }
            3->{ FourthFloor() }
            4->{ FivthFloor() }
            5->{ SixthFloor() }
            6->{ SeventhFloor() }
            else->{ Fragment() }
        }
    }
}