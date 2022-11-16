package com.example.homework6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework6.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment :Fragment(){

    private  lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeVPAdapter=HomeVPAdapter(this)
        binding.vpHome.adapter=homeVPAdapter

        val tabTitleArray=arrayOf(
            "Menu",
            "MyPage",
            "Setting"

        )

        TabLayoutMediator(binding.tabHome,binding.vpHome){tab,position->
            tab.text=tabTitleArray[position]
        }.attach()


    }

}
