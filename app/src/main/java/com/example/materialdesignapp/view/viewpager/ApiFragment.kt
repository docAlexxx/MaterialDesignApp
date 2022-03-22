package com.example.materialdesignapp.view.viewpager

import android.os.Bundle
import android.view.View
import com.example.materialdesignapp.databinding.FragmentApiBinding
import com.example.materialdesignapp.utils.BindingFragment


class ApiFragment : BindingFragment<FragmentApiBinding>(FragmentApiBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)

    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")

}