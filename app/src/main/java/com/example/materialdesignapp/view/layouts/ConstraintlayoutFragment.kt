package com.example.materialdesignapp.view.layouts

import android.os.Bundle
import android.view.View
import com.example.materialdesignapp.databinding.FragmentConstraintlayoutBinding
import com.example.materialdesignapp.utils.BindingFragment

class ConstraintlayoutFragment: BindingFragment<FragmentConstraintlayoutBinding>(FragmentConstraintlayoutBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")

}