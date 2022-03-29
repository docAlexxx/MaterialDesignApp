package com.example.materialdesignapp.view.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import com.example.materialdesignapp.databinding.FragmentObjectanimatorBinding
import com.example.materialdesignapp.utils.BindingFragment

class ObjectanimatorFragment :
    BindingFragment<FragmentObjectanimatorBinding>(FragmentObjectanimatorBinding::inflate) {

    private var isHide = true
    private val duration = 1000L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transparentBackground.alpha = 0f
        binding.optionOneContainer.alpha = 0f
        binding.optionTwoContainer.alpha = 0f
        binding.optionOneContainer.isClickable = false
        binding.optionTwoContainer.isClickable = false

        binding.fab.setOnClickListener {

            if (isHide) {
                animateViews(0f, 405f, 0f, -130f, 0f, -260f, 0.5f)
                changeViews(1f, 0.9f, true)
            } else {
                animateViews(405f, 0f, -130f, 0f, -260f, 0f, 1f)
                changeViews(0f, 0f, false)
            }

            isHide = !isHide

        }

    }

    fun animateViews(
        start: Float,
        rotate: Float,
        startOne: Float,
        endOne: Float,
        startTwo: Float,
        endTwo: Float,
        scale: Float
    ) {
        ObjectAnimator.ofFloat(binding.plusImageview, View.ROTATION, start, rotate)
            .setDuration(duration).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, startOne, endOne)
            .setDuration(duration).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, startTwo, endTwo)
            .setDuration(duration).start()
        ObjectAnimator.ofFloat(binding.fab, View.SCALE_X, scale)
            .setDuration(duration).start()
        ObjectAnimator.ofFloat(binding.fab, View.SCALE_Y, scale)
            .setDuration(duration).start()
    }

    fun changeViews(alphaMenu: Float, alphaBack: Float, clickable: Boolean) {
        visibleViews(alphaMenu, clickable, binding.optionTwoContainer)
        visibleViews(alphaMenu, clickable, binding.optionOneContainer)
        visibleViews(alphaBack, clickable, binding.transparentBackground)
    }

    fun visibleViews(alphaView: Float, clickable: Boolean, element: View) {
        element.animate()
            .alpha(alphaView)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    element.isClickable = clickable
                    super.onAnimationEnd(animation)
                }
            })
    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")

}


