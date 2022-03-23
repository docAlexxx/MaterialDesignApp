package com.example.materialdesignapp.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.BottomNavigationLayoutBinding
import com.example.materialdesignapp.view.bottomnavigation.ApiBottomActivity
import com.example.materialdesignapp.view.layouts.ConstraintlayoutFragment
import com.example.materialdesignapp.view.viewpager.ApiFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    val binding: BottomNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.navigation_one -> {
                    openFragment(ApiFragment())
                }
                R.id.navigation_two -> {
                    // Toast.makeText(requireContext(), "navigation_two", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), ApiBottomActivity::class.java))
                }
                R.id.constraint -> {
                    openFragment(ConstraintlayoutFragment())
                }
            }
            // BottomNavigationDrawerFragment().dismiss()
            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
            .addToBackStack("")
            .commit()
    }

}