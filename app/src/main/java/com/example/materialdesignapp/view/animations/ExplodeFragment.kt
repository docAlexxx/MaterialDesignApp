package com.example.materialdesignapp.view.animations

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.Transition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.example.materialdesignapp.R
import com.example.materialdesignapp.databinding.FragmentExplodeBinding
import com.example.materialdesignapp.utils.BindingFragment

class ExplodeFragment :
    BindingFragment<FragmentExplodeBinding>(FragmentExplodeBinding::inflate) {

    private var isBeforeExplode = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.explodeRecyclerView.adapter = MyAdapter()
    }

    inner class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_explode_recycler_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val transitionSet = TransitionSet()
                val explode = Explode()
                explode.duration = 3000
                val rect = Rect()
                explode.excludeTarget(it, true)
                it.getGlobalVisibleRect(rect)
                // val rect2 = Rect(it.x.toInt(),it.y.toInt(),it.x.toInt()+it.width,it.y.toInt()+it.height)
                explode.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return rect
                    }
                }
                transitionSet.addTransition(explode)
                TransitionManager.beginDelayedTransition(
                    binding.transitionsContainer,
                    transitionSet
                )

                for (i in 0..itemCount - 1) {
                    if (i != position) {
                        binding.explodeRecyclerView[i].visibility =
                            if (isBeforeExplode) View.GONE else View.VISIBLE
                    }
                }
                isBeforeExplode = !isBeforeExplode
            }
        }

        override fun getItemCount(): Int {
            return 96
        }

    }

    override val viewModel: Any
        get() = TODO("Not yet implemented")
}
