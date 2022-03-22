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
        binding.tabLayout.getTabAt(EARTH)?.setIcon(R.drawable.ic_earth)
        binding.tabLayout.getTabAt(MARS)?.setIcon(R.drawable.ic_mars)
        binding.tabLayout.getTabAt(SYSTEM)?.setIcon(R.drawable.ic_system)
    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")

}