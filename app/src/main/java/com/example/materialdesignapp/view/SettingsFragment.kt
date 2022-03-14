package com.example.materialdesignapp.view

import android.os.Bundle
import android.view.View
import com.example.materialdesignapp.databinding.FragmentSettingsBinding
import com.example.materialdesignapp.utils.BindingFragment
import com.google.android.material.chip.Chip

class SettingsFragment : BindingFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            binding.chipGroup.findViewById<Chip>(checkedId)?.let{ it->
               // Toast.makeText(requireContext(), "${it.text} ${checkedId}", Toast.LENGTH_SHORT).show()
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