package com.example.materialdesignapp.view.animations

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.PathMotion
import androidx.transition.TransitionManager
import com.example.materialdesignapp.databinding.FragmentMotionBinding
import com.example.materialdesignapp.utils.BindingFragment
import com.google.android.material.transition.MaterialArcMotion

class MotionFragment :
    BindingFragment<FragmentMotionBinding>(FragmentMotionBinding::inflate) {

    private var buttonsPosition = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonMain.setOnClickListener {
                val changeBounds = ChangeBounds()
                changeBounds.setPathMotion(ArcMotion())
                changeBounds.duration = 1500

                TransitionManager.beginDelayedTransition(transitionsContainer, changeBounds)

                val paramsOne = buttonOne.layoutParams as FrameLayout.LayoutParams
                val paramsTwo = buttonTwo.layoutParams as FrameLayout.LayoutParams
                val paramsThree = buttonThree.layoutParams as FrameLayout.LayoutParams
                val paramsFour = buttonFour.layoutParams as FrameLayout.LayoutParams

                when (buttonsPosition) {
                    1-> {
                        paramsOne.gravity=Gravity.BOTTOM or Gravity.START
                        paramsTwo.gravity=Gravity.BOTTOM or Gravity.END
                        paramsThree.gravity=Gravity.TOP or Gravity.END
                        paramsFour.gravity=Gravity.TOP or Gravity.START
                    }
                    2-> {
                        paramsOne.gravity=Gravity.BOTTOM or Gravity.END
                        paramsTwo.gravity=Gravity.TOP or Gravity.END
                        paramsThree.gravity=Gravity.TOP or Gravity.START
                        paramsFour.gravity=Gravity.BOTTOM or Gravity.START
                    }
                    3-> {
                        paramsOne.gravity=Gravity.TOP or Gravity.END
                        paramsTwo.gravity=Gravity.TOP or Gravity.START
                        paramsThree.gravity=Gravity.BOTTOM or Gravity.START
                        paramsFour.gravity=Gravity.BOTTOM or Gravity.END
                    }
                    4-> {
                        paramsOne.gravity=Gravity.TOP or Gravity.START
                        paramsTwo.gravity=Gravity.BOTTOM or Gravity.START
                        paramsThree.gravity=Gravity.BOTTOM or Gravity.END
                        paramsFour.gravity=Gravity.TOP or Gravity.END
                    }

                }

                if (buttonsPosition == 4) buttonsPosition = 1 else buttonsPosition++

                buttonOne.layoutParams = paramsOne
                buttonTwo.layoutParams = paramsTwo
                buttonThree.layoutParams = paramsThree
                buttonFour.layoutParams = paramsFour

            }

        }

    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")

}


