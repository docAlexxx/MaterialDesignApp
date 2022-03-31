package com.example.materialdesignapp.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignapp.databinding.ActivityRecyclerItemEarthBinding
import com.example.materialdesignapp.databinding.ActivityRecyclerItemHeaderBinding
import com.example.materialdesignapp.databinding.ActivityRecyclerItemMarsBinding

class RecyclerActivityAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var dataItem: MutableList<Data>
) : RecyclerView.Adapter<RecyclerActivityAdapter.BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return dataItem[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            TYPE_EARTH -> {
                val itemBinding: ActivityRecyclerItemEarthBinding =
                    ActivityRecyclerItemEarthBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return EarthViewHolder(itemBinding.root)
            }
            TYPE_MARS -> {
                val itemBinding: ActivityRecyclerItemMarsBinding =
                    ActivityRecyclerItemMarsBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return MarsViewHolder(itemBinding.root)
            }

            else -> {
                val itemBinding: ActivityRecyclerItemHeaderBinding =
                    ActivityRecyclerItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                return HeaderViewHolder(itemBinding.root)
            }
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(dataItem[position])
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Data)
    }

    fun addItem() {
        dataItem.add(generateNewItem())
        notifyItemInserted(itemCount - 1)
    }


    private fun generateNewItem() = Data("new Mars", type = TYPE_MARS)

    override fun getItemCount() = dataItem.size

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            ActivityRecyclerItemEarthBinding.bind(itemView).apply {
                earthImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }


    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            ActivityRecyclerItemMarsBinding.bind(itemView).apply {
                marsImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
                addItemImageView.setOnClickListener { addItemByPosition() }
                removeItemImageView.setOnClickListener { removeItem() }
                moveItemUp.setOnClickListener { if (layoutPosition>1) moveUp() }
                moveItemDown.setOnClickListener { if (layoutPosition<dataItem.size-1) moveDown() }
            }
        }

        private fun addItemByPosition() {
            dataItem.add(layoutPosition + 1, generateNewItem())
            notifyItemInserted(layoutPosition + 1)
        }

        private fun removeItem() {
            dataItem.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        private fun moveUp() {
            dataItem.removeAt(layoutPosition).apply {
                dataItem.add(layoutPosition - 1, this)
            }
            notifyItemMoved(layoutPosition, layoutPosition - 1)
        }

        private fun moveDown() {
            dataItem.removeAt(layoutPosition).apply {
                dataItem.add(layoutPosition + 1, this)
            }
            notifyItemMoved(layoutPosition, layoutPosition + 1)
        }

    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            ActivityRecyclerItemHeaderBinding.bind(itemView).apply {
                header.setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }
}