package com.example.materialdesignapp.view.viewpager

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import com.example.materialdesignapp.databinding.FragmentSystemBinding
import com.example.materialdesignapp.utils.BindingFragment


class SystemFragment : BindingFragment<FragmentSystemBinding>(FragmentSystemBinding::inflate) {

    private var isOnCenter = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewSystem.setOnClickListener {
            isOnCenter = !isOnCenter

            val changeBounds = ChangeBounds()
            val changeImageTransform = ChangeImageTransform()
            changeBounds.duration = 3000
            changeImageTransform.duration = 3000
            TransitionManager.beginDelayedTransition(
                binding.transitionsContainer,
                changeImageTransform
            )
            //   with(binding.imageViewSystem.scaleType) {
            if (isOnCenter) {
                binding.imageViewSystem.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                binding.imageViewSystem.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            //    }
        }

    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")
}