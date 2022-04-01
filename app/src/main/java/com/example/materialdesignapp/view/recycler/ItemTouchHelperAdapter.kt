package com.example.materialdesignapp.view.recycler

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}

interface ItemTouchHelperViewAdapter {
    fun onItemSelected()
    fun onItemClear()
}