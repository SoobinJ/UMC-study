package com.example.homework6

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeVPAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->OneFragment()
            1->TowFragment()
            2->ThirdFragment()
            else->OneFragment()
        }
    }

}