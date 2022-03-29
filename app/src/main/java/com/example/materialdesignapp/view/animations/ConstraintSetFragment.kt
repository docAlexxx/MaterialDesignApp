package com.example.materialdesignapp.view.animations

import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentConstraintSetStartBinding
import com.example.materialdesignapp.utils.BindingFragment

class ConstraintSetFragment :
    BindingFragment<FragmentConstraintSetStartBinding>(FragmentConstraintSetStartBinding::inflate) {

    private var textIsHidden = true
    private val duration = 2000L
    private val mTension = 7f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backgroundImage.setOnClickListener {
            textIsHidden = !textIsHidden
            if (textIsHidden) {
                val constraintSet = ConstraintSet()
                constraintSet.clone(requireContext(), R.layout.fragment_constraint_set_start)
                val changeBounds = ChangeBounds()
                changeBounds.duration = duration
                changeBounds.interpolator = AnticipateOvershootInterpolator(mTension)
                TransitionManager.beginDelayedTransition(binding.constraintContainer, changeBounds)
                constraintSet.applyTo(binding.constraintContainer)
            } else {
                val constraintSet = ConstraintSet()
                constraintSet.clone(requireContext(), R.layout.fragment_constraint_set_end)
                /*constraintSet.connect(R.id.title,ConstraintSet.END,R.id.constraint_container,ConstraintSet.END)
                constraintSet.connect(R.id.title,ConstraintSet.START,R.id.constraint_container,ConstraintSet.START)
                constraintSet.connect(R.id.title,ConstraintSet.TOP,R.id.constraint_container,ConstraintSet.TOP)*/

                val changeBounds = ChangeBounds()
                changeBounds.duration = duration
                changeBounds.interpolator = BounceInterpolator()
                TransitionManager.beginDelayedTransition(binding.constraintContainer, changeBounds)
                constraintSet.applyTo(binding.constraintContainer)
            }

        }
    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")
}