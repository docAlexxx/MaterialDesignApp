package com.example.materialdesignapp.view.viewpager

import android.os.Bundle
import android.view.View
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentApiBinding
import com.example.materialdesignapp.utils.BindingFragment


class ApiFragment : BindingFragment<FragmentApiBinding>(FragmentApiBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.tabLayout.getTabAt(EARTH)?.customView = layoutInflater.inflate(R.layout.activity_api_tablayout_item_earth,null)
        binding.tabLayout.getTabAt(MARS)?.customView = layoutInflater.inflate(R.layout.activity_api_tablayout_item_mars,null)
        binding.tabLayout.getTabAt(SYSTEM)?.customView = layoutInflater.inflate(R.layout.activity_api_tablayout_item_system,null)

     /*   binding.tabLayout.getTabAt(EARTH)?.setIcon(R.drawable.ic_earth)
        binding.tabLayout.getTabAt(MARS)?.setIcon(R.drawable.ic_mars)
        binding.tabLayout.getTabAt(SYSTEM)?.setIcon(R.drawable.ic_system)*/


    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")

}