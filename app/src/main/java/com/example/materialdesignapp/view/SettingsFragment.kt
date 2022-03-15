package com.example.materialdesignapp.view

import android.os.Bundle
import android.view.View
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentSettingsBinding
import com.example.materialdesignapp.utils.BindingFragment

var curTheme = R.style.MyThemeMars

class SettingsFragment :
    BindingFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themeChoose()
    }

    fun themeChoose() {
        binding.chipGroupTheme.setOnCheckedChangeListener { group, checkedId ->
            with(getActivity()) {
                when (checkedId) {
                    4 -> {
                        curTheme = R.style.MyThemeMars
                    }
                    5 -> {
                        curTheme = R.style.MyThemeSky
                    }
                    6 -> {
                        curTheme = R.style.MyThemeEarth
                    }
                }
                this?.recreate()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")

}