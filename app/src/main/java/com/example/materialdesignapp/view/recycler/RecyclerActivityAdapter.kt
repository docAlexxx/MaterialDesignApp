package com.example.materialdesignapp.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignapp.databinding.ActivityRecyclerItemEarthBinding
import com.example.materialdesignapp.databinding.ActivityRecyclerItemMarsBinding

class RecyclerActivityAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: List<Data>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_EARTH) {
            val itemBinding: ActivityRecyclerItemEarthBinding =
                ActivityRecyclerItemEarthBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            return EarthViewHolder(itemBinding.root)
        } else {
            val itemBinding: ActivityRecyclerItemMarsBinding =
                ActivityRecyclerItemMarsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            return MarsViewHolder(itemBinding.root)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_EARTH) {
            (holder as EarthViewHolder).bind(data[position])
        } else {
            (holder as MarsViewHolder).bind(data[position])
        }
    }

    override fun getItemCount() = data.size

    inner class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            ActivityRecyclerItemEarthBinding.bind(itemView).apply {
                earthImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            ActivityRecyclerItemMarsBinding.bind(itemView).apply {
                marsImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }
}