package com.example.materialdesignapp.view.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignapp.databinding.ActivityRecyclerItemEarthBinding
import com.example.materialdesignapp.databinding.ActivityRecyclerItemHeaderBinding
import com.example.materialdesignapp.databinding.ActivityRecyclerItemMarsBinding

class RecyclerActivityAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var dataItems: MutableList<Pair<Boolean, Data>>
) : RecyclerView.Adapter<RecyclerActivityAdapter.BaseViewHolder>(), ItemTouchHelperAdapter {

    override fun getItemViewType(position: Int): Int {
        return dataItems[position].second.type
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
        holder.bind(dataItems[position])
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<Boolean, Data>)
    }

    fun addItem() {
        dataItems.add(generateNewItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateNewItem() = Pair(false, Data("new Mars", type = TYPE_MARS))

    override fun getItemCount() = dataItems.size

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Boolean, Data>) {
            ActivityRecyclerItemEarthBinding.bind(itemView).apply {
                earthImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data.second)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewAdapter {
        override fun bind(data: Pair<Boolean, Data>) {
            ActivityRecyclerItemMarsBinding.bind(itemView).apply {
                marsImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data.second)
                }
                addItemImageView.setOnClickListener { addItemByPosition() }
                removeItemImageView.setOnClickListener { removeItem() }
                moveItemUp.setOnClickListener { if (layoutPosition > 1) moveUp() }
                moveItemDown.setOnClickListener { if (layoutPosition < dataItems.size - 1) moveDown() }
                marsTextView.setOnClickListener { showDescription() }
                marsDescriptionTextView.visibility =
                    if (data.first == false) View.GONE else View.VISIBLE

            }
        }

        private fun showDescription() {
            dataItems[layoutPosition] = dataItems[layoutPosition].let {
                val currentState = !it.first
                Pair(currentState, it.second)
            }
            notifyItemChanged(layoutPosition)

        }

        private fun addItemByPosition() {
            dataItems.add(layoutPosition + 1, generateNewItem())
            notifyItemInserted(layoutPosition + 1)
        }

        private fun removeItem() {
            dataItems.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        private fun moveUp() {
            dataItems.removeAt(layoutPosition).apply {
                dataItems.add(layoutPosition - 1, this)
            }
            notifyItemMoved(layoutPosition, layoutPosition - 1)
        }

        private fun moveDown() {
            dataItems.removeAt(layoutPosition).apply {
                dataItems.add(layoutPosition + 1, this)
            }
            notifyItemMoved(layoutPosition, layoutPosition + 1)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }


    }

    inner class HeaderViewHolder(view: View) : RecyclerActivityAdapter.BaseViewHolder(view) {
        override fun bind(data: Pair<Boolean, Data>) {
            ActivityRecyclerItemHeaderBinding.bind(itemView).apply {
                header.setOnClickListener {
                    onListItemClickListener.onItemClick(data.second)
                }
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {

        if (toPosition > 0) {
            dataItems.removeAt(fromPosition).apply {
                dataItems.add(toPosition, this)
            }
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    override fun onItemDismiss(position: Int) {
        dataItems.removeAt(position)
        notifyItemRemoved(position)
    }


}