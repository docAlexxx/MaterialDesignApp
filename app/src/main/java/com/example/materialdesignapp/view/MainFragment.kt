package com.example.materialdesignapp.view

import com.example.materialdesignapp.databinding.FragmentMainBinding
import com.example.materialdesignapp.utils.BindingFragment

class MainFragment : BindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object {

        @JvmStatic
        fun newInstance() =  MainFragment()
    }
}