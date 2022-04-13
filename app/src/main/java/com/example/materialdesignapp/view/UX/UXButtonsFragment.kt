package com.example.materialdesignapp.view.UX

import android.os.Bundle
import android.view.View
import com.example.materialdesignapp.databinding.FragmentUxButtonsBinding
import com.example.materialdesignapp.databinding.FragmentUxTextBinding
import com.example.materialdesignapp.utils.BindingFragment


class UXButtonsFragment :
    BindingFragment<FragmentUxButtonsBinding>(FragmentUxButtonsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance() = UXButtonsFragment()
    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")

}