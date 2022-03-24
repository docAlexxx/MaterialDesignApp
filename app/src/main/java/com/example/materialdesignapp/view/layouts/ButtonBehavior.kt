package com.example.materialdesignapp.view.layouts

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class ButtonBehavior(context:Context, attributeSet: AttributeSet) : CoordinatorLayout.Behavior<View>(context,attributeSet) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        var bar = dependency as AppBarLayout
        val barHeight = (bar.height*0.75).toFloat()
        if(abs(bar.y)>barHeight){
            child.visibility = View.GONE
        }else{
            child.visibility = View.VISIBLE
            val alpha = (barHeight+bar.y)/barHeight
            child.alpha =alpha
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}