package com.example.materialdesignapp.view.animations

import android.os.Bundle
import android.view.View
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
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
            fade.duration = 1000
            changeBounds.duration = 1000
            transitionSet.ordering = TransitionSet.ORDERING_TOGETHER
            transitionSet.addTransition(fade)
            transitionSet.addTransition(changeBounds)
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