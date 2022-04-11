package com.example.materialdesignapp.view.layouts

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentCoordinatorlayoutBinding
import com.example.materialdesignapp.utils.BindingFragment

class CoordinatorlayoutFragment : BindingFragment<FragmentCoordinatorlayoutBinding>(
    FragmentCoordinatorlayoutBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFont()

    }

    private fun setFont() {
        val request = FontRequest("com.google.android.gms.fonts","com.google.android.gms","Lemonada",
            R.array.com_google_android_gms_fonts_certs)

        val callback = object : FontsContractCompat.FontRequestCallback(){
            override fun onTypefaceRetrieved(typeface: Typeface?) {
                super.onTypefaceRetrieved(typeface)
                binding.coordTextView.typeface =typeface

            }
        }
        FontsContractCompat.requestFont(requireContext(),request,callback,
            Handler(requireActivity().mainLooper)
        )
    }


    override val viewModel: Any
        get() = TODO("Not yet implemented")

}