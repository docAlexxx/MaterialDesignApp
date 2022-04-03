package com.example.materialdesignapp.view.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesignapp.databinding.ActivityNotesRecyclerBinding

class RecyclerActivityNotes : AppCompatActivity() {

    lateinit var binding: ActivityNotesRecyclerBinding
    lateinit var adapterNotes: RecyclerActivityNotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = arrayListOf(
            Pair(false, Data("", type = TYPE_HEADER)),
            Pair(false, Data("Note 1", type = TYPE_NOTES)),
        )


        adapterNotes = RecyclerActivityNotesAdapter({
            Toast.makeText(this, it.someText, Toast.LENGTH_SHORT).show()
        }, data)

        binding.recyclerView.adapter = adapterNotes

        binding.recyclerActivityFAB.setOnClickListener {
            adapterNotes.addItem()
            binding.recyclerView.smoothScrollToPosition(adapterNotes.itemCount - 1)
        }

        binding.filterInputLayout.setEndIconOnClickListener {

            data.filter {
                val textFind = binding.filterEditText.text.toString()
                it.second.someText.contains(textFind)
            }
            adapterNotes.notifyDataSetChanged()

        }

        ItemTouchHelper(ItemTouchHelperCallback(adapterNotes)).attachToRecyclerView(binding.recyclerView)
    }

    class ItemTouchHelperCallback(private val adapter: RecyclerActivityNotesAdapter) :
        ItemTouchHelper.Callback() {

        override fun isLongPressDragEnabled(): Boolean {
            return true
        }

        override fun isItemViewSwipeEnabled(): Boolean {
            return super.isItemViewSwipeEnabled()
        }

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val drag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipe = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(drag, swipe)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            if (viewHolder is RecyclerActivityNotesAdapter.NotesViewHolder) {
                adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            } else return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.onItemDismiss(viewHolder.adapterPosition)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (viewHolder !is RecyclerActivityNotesAdapter.NotesViewHolder) {
                return super.onSelectedChanged(viewHolder, actionState)
            }
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                (viewHolder as ItemTouchHelperViewAdapter).onItemSelected()
            }

        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {

            if (viewHolder !is RecyclerActivityNotesAdapter.NotesViewHolder) {
                return super.clearView(recyclerView, viewHolder)
            }
            (viewHolder as ItemTouchHelperViewAdapter).onItemClear()
            super.clearView(recyclerView, viewHolder)
        }


    }
}