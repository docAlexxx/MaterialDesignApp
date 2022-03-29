package com.example.materialdesignapp.view.animations

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.transition.*
import com.example.materialdesignapp.databinding.FragmentTransitionBinding
import com.example.materialdesignapp.utils.BindingFragment

class TransitionFragment :
    BindingFragment<FragmentTransitionBinding>(FragmentTransitionBinding::inflate) {

    private var textTopISVisible = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {

            val transitionSet = TransitionSet()
            val fade = Fade()
            val changeBounds = ChangeBounds()
            val slide = Slide(Gravity.START)
            fade.duration = 2000
            changeBounds.duration = 2000
            slide.duration = 2000
            transitionSet.ordering = TransitionSet.ORDERING_TOGETHER
            transitionSet.addTransition(fade)
            transitionSet.addTransition(changeBounds)
            transitionSet.addTransition(slide)
            TransitionManager.beginDelayedTransition(binding.transitionsContainer, transitionSet)

            textTopISVisible = !textTopISVisible
            with(binding) {
                if (textTopISVisible) {
                    textTop.visibility = View.VISIBLE
                    textBottom.visibility = View.GONE
                    button.textSize = 15f
                } else {
                    textTop.visibility = View.GONE
                    textBottom.visibility = View.VISIBLE
                    button.textSize = 45f
                }
            }
        }

    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")
}